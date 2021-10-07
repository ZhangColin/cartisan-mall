package com.cartisan.mall.domains.order;

import com.cartisan.mall.domains.product.Product;
import com.cartisan.mall.domains.product.ProductRepository;
import com.cartisan.mall.domains.product.ProductStatusEnum;
import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author colin
 */
@Service
public class CartServiceImpl implements CartService {
    public static final String CART_REDIS_KEY_TEMPLATE = "cart_%d";

    private final ProductRepository productRepository;
    private final HashOperations<String, String, String> redisHash;
    private final Gson gson;

    public CartServiceImpl(ProductRepository productRepository, HashOperations<String, String, String> redisHash) {
        this.productRepository = productRepository;
        this.redisHash = redisHash;
        gson = new Gson();
    }

    @Override
    public CartDTO add(AddCartCommand command) {
        // 商品是否存在
        final Product product = productRepository.findById(command.getProductId())
                .orElseThrow(() -> new RuntimeException("商品不存在"));

        // 商品是否正常在售
        if (!product.getStatus().equals(ProductStatusEnum.ON_SALE.getCode())) {
            throw new RuntimeException("商品下回或已删除");
        }
        // 商品库存是否充足
        if (product.getStock() <= 0) {
            throw new RuntimeException("商品库存不足");
        }

        // 写入Redis
        final String redisKey = String.format(CART_REDIS_KEY_TEMPLATE, command.getUserId());

        final String value = redisHash.get(redisKey, command.getProductId().toString());

        CartPO cartPO = null;
        if (StringUtils.isEmpty(value)) {
            cartPO = new CartPO(command.getProductId(), 1, command.getSelected());
        } else {
            cartPO = gson.fromJson(value, CartPO.class);
            cartPO.setQuantity(cartPO.getQuantity() + 1);
        }
        redisHash.put(redisKey,
                command.getProductId().toString(),
                gson.toJson(cartPO));

        return list(command.getUserId());
    }

    @Override
    public CartDTO list(Long userId) {
        final String redisKey = String.format(CART_REDIS_KEY_TEMPLATE, userId);
        final Map<String, String> entries = redisHash.entries(redisKey);

        final List<CartProductDTO> cartProductDTOS = entries.entrySet().stream().map(entry -> {
            final Long productId = Long.valueOf(entry.getKey());

            // TODO: 使用 in 优化
            final Product product = productRepository.findById(productId).get();
            final CartPO cartPO = gson.fromJson(entry.getValue(), CartPO.class);

            final CartProductDTO cartProductDTO = new CartProductDTO(product.getId(), cartPO.getQuantity(),
                    product.getName(), product.getSubTitle(), product.getMainImage(), product.getPrice(),
                    product.getStatus(), product.getPrice().multiply(BigDecimal.valueOf(cartPO.getQuantity())),
                    product.getStock(), cartPO.getProductSelected());

            return cartProductDTO;
        }).collect(Collectors.toList());

        final CartDTO cartDTO = new CartDTO();
        cartDTO.setCartProductDTOList(cartProductDTOS);
        cartDTO.setSelectedAll(cartProductDTOS.stream().allMatch(CartProductDTO::getProductSelected));

        // 只计算选中的
        cartDTO.setCartTotalQuantity(cartProductDTOS.stream()
                .filter(CartProductDTO::getProductSelected)
                .map(CartProductDTO::getQuantity)
                .reduce(0, Integer::sum));
        cartDTO.setCartTotalPrice(cartProductDTOS.stream()
                .filter(CartProductDTO::getProductSelected)
                .map(CartProductDTO::getProductTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add));

        return cartDTO;
    }

    @Override
    public CartDTO update(UpdateCartCommand command) {
        final String redisKey = String.format(CART_REDIS_KEY_TEMPLATE, command.getUserId());

        final String value = redisHash.get(redisKey, command.getProductId().toString());

        if (StringUtils.isEmpty(value)) {
            throw new RuntimeException("购物车中的商品不存在");
        }

        CartPO cartPO = gson.fromJson(value, CartPO.class);
        if (command.getQuantity() != null && command.getQuantity() >= 0) {
            cartPO.setQuantity(command.getQuantity());
        }
        if (command.getSelected() != null) {
            cartPO.setProductSelected(command.getSelected());
        }
        redisHash.put(redisKey,
                command.getProductId().toString(),
                gson.toJson(cartPO));

        return list(command.getUserId());

    }

    @Override
    public CartDTO delete(DeleteCartCommand command) {
        final String redisKey = String.format(CART_REDIS_KEY_TEMPLATE, command.getUserId());

        final String value = redisHash.get(redisKey, command.getProductId().toString());

        if (StringUtils.isEmpty(value)) {
            throw new RuntimeException("购物车中的商品不存在");
        }
        redisHash.delete(redisKey,
                command.getProductId().toString());

        return list(command.getUserId());
    }

    @Override
    public CartDTO selectAll(Long userId) {
        final String redisKey = String.format(CART_REDIS_KEY_TEMPLATE, userId);
        final Map<String, String> entries = redisHash.entries(redisKey);

        entries.forEach((key, value) -> {
            final CartPO cartPO = gson.fromJson(value, CartPO.class);
            cartPO.setProductSelected(true);

            redisHash.put(redisKey,
                    key,
                    gson.toJson(cartPO));
        });

        return list(userId);
    }

    @Override
    public CartDTO unSelectAll(Long userId) {
        final String redisKey = String.format(CART_REDIS_KEY_TEMPLATE, userId);
        final Map<String, String> entries = redisHash.entries(redisKey);

        entries.forEach((key, value) -> {
            final CartPO cartPO = gson.fromJson(value, CartPO.class);
            cartPO.setProductSelected(false);

            redisHash.put(redisKey,
                    key,
                    gson.toJson(cartPO));
        });

        return list(userId);
    }

    @Override
    public Integer sum(Long userId) {
        final String redisKey = String.format(CART_REDIS_KEY_TEMPLATE, userId);
        final Map<String, String> entries = redisHash.entries(redisKey);

        return entries.entrySet().stream()
                .map(entry->gson.fromJson(entry.getValue(), CartPO.class))
                .filter(CartPO::getProductSelected)
                .map(CartPO::getQuantity)
                .reduce(0, Integer::sum);
    }
}
