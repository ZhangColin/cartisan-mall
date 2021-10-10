package com.cartisan.mall.domains.order;

import com.cartisan.dtos.PageResult;
import com.cartisan.mall.domains.product.Product;
import com.cartisan.mall.domains.product.ProductRepository;
import com.cartisan.mall.domains.shipping.Shipping;
import com.cartisan.mall.domains.shipping.ShippingRepository;
import com.cartisan.utils.SnowflakeIdWorker;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

/**
 * @author colin
 */
@Service
public class OrderServiceImpl implements OrderService {
    private ShippingRepository shippingRepository;
    private final CartService cartService;
    private final ProductRepository productRepository;
    private final SnowflakeIdWorker idWorker;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    public OrderServiceImpl(ShippingRepository shippingRepository,
                            CartService cartService,
                            ProductRepository productRepository,
                            SnowflakeIdWorker idWorker,
                            OrderRepository orderRepository,
                            OrderItemRepository orderItemRepository) {
        this.shippingRepository = shippingRepository;
        this.cartService = cartService;
        this.productRepository = productRepository;
        this.idWorker = idWorker;
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }


    @Override
    @Transactional
    public OrderDTO create(Long userId, Long shippingId) {
        // 收货地址校验
        final Shipping shipping = shippingRepository.findByUserIdAndId(userId, shippingId)
                .orElseThrow(() -> new RuntimeException("地址不存在"));

        // 获取购物车，只获取选中的商品
        final List<CartProductDTO> cartProductDTOS = cartService.list(userId)
                .getCartProductDTOList().stream()
                .filter(CartProductDTO::getProductSelected)
                .collect(toList());

        if (CollectionUtils.isEmpty(cartProductDTOS)) {
            throw new RuntimeException("请选择商品后下单");
        }

        final Map<Long, Product> productMap = productRepository
                .findByIdIn(cartProductDTOS.stream().map(CartProductDTO::getProductId).collect(toList()))
                .stream().collect(toMap(Product::getId, product -> product));

        final long orderNo = idWorker.nextId();


        List<OrderItem> items = new ArrayList<>();
        for (CartProductDTO cartProductDTO : cartProductDTOS) {
            if (!productMap.containsKey(cartProductDTO.getProductId())) {
                throw new RuntimeException("商品不存在，productId=" + cartProductDTO.getProductId());
            }

            final Product product = productMap.get(cartProductDTO.getProductId());

            if (product.getStatus() != 1) {
                throw new RuntimeException("商品不是在售状态，productName=" + product.getName());
            }

            if (product.getStock() < cartProductDTO.getQuantity()) {
                throw new RuntimeException("商品库存不足，productName=" + cartProductDTO.getProductName());
            }

            final OrderItem orderItem = new OrderItem();
            orderItem.setUserId(userId);
            orderItem.setOrderNo(orderNo);
            orderItem.setProductId(product.getId());
            orderItem.setProductName(product.getName());
            orderItem.setProductImage(product.getMainImage());
            orderItem.setCurrentUnitPrice(product.getPrice());
            orderItem.setQuantity(cartProductDTO.getQuantity());
            orderItem.setTotalPrice(product.getPrice().multiply(BigDecimal.valueOf(cartProductDTO.getQuantity())));

            items.add(orderItem);

            // 减库存
            product.setStock(product.getStock() - orderItem.getQuantity());
            productRepository.save(product);
        }


        // 生成订单，入库
        final Order order = new Order();
        order.setOrderNo(orderNo);
        order.setUserId(userId);
        order.setShippingId(shippingId);
        // 计算总价
        order.setPayment(items.stream().map(OrderItem::getTotalPrice).reduce(BigDecimal.ZERO, BigDecimal::add));
        order.setPaymentType(PaymentType.PAY_ONLINE.getCode());

        order.setPostage(BigDecimal.ZERO);
        order.setStatus(OrderStatusEnum.NO_PAY.getCode());

        orderRepository.save(order);

        orderItemRepository.saveAll(items);

        // 更新购物车（清除选中的商品）
        cartProductDTOS.forEach(cartProductDTO -> {
            final DeleteCartCommand command = new DeleteCartCommand();
            command.setUserId(userId);
            command.setProductId(cartProductDTO.getProductId());
            cartService.delete(command);
        });

        // 返回 orderDTO
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(order, orderDTO);

        orderDTO.setItems(items.stream().map(item->{
            final OrderItemDTO orderItemDTO = new OrderItemDTO();
            BeanUtils.copyProperties(item, orderItemDTO);
            return orderItemDTO;
        }).collect(toList()));

        orderDTO.setShipping(shipping);
        orderDTO.setShippingId(shipping.getId());


        return orderDTO;
    }

    @Override
    public PageResult<OrderInfo> list(Long userId, Pageable pageable) {
        final Page<Order> orders = orderRepository.findByUserId(userId, pageable);
        return new PageResult<>(orders.getTotalElements(), orders.getTotalPages(),
                orders.getContent().stream().map(order->{
                    final OrderInfo orderInfo = new OrderInfo();
                    BeanUtils.copyProperties(order, orderInfo);
                    return orderInfo;
                }).collect(toList()));
    }

    @Override
    public OrderDTO detail(Long userId, Long orderNo) {
        final Order order = orderRepository.findByUserIdAndOrderNo(userId, orderNo)
                .orElseThrow(() -> new RuntimeException("订单不存在"));

        final OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(order, orderDTO);

        orderDTO.setItems(orderItemRepository.findByOrderNo(orderNo)
                .stream().map(item->{
                    final OrderItemDTO orderItemDTO = new OrderItemDTO();
                    BeanUtils.copyProperties(item, orderItemDTO);

                    return orderItemDTO;
                }).collect(toList()));

        orderDTO.setShipping(shippingRepository.findById(order.getShippingId()).get());

        return orderDTO;
    }

    @Override
    public void cancel(Long userId, Long orderNo) {
        final Order order = orderRepository.findByUserIdAndOrderNo(userId, orderNo)
                .orElseThrow(() -> new RuntimeException("订单不存在"));

        if (!order.getStatus().equals(OrderStatusEnum.NO_PAY.getCode())) {
            throw new RuntimeException("订单状态不正确");
        }

        order.setStatus(OrderStatusEnum.CANCELED.getCode());
        order.setCloseTime(LocalDateTime.now());

        orderRepository.save(order);
    }

    @Override
    public void paid(Long orderNo) {
        final Order order = orderRepository.findByOrderNo(orderNo)
                .orElseThrow(() -> new RuntimeException("订单不存在"));

        if (!order.getStatus().equals(OrderStatusEnum.NO_PAY.getCode())) {
            throw new RuntimeException("订单状态不正确");
        }

        order.setStatus(OrderStatusEnum.PAID.getCode());
        order.setPaymentTime(LocalDateTime.now());

        orderRepository.save(order);
    }
}
