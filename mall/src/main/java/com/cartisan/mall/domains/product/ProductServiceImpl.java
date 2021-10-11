package com.cartisan.mall.domains.product;

import com.cartisan.dto.PageResult;
import com.cartisan.mall.domains.category.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author colin
 */
@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    public ProductServiceImpl(ProductRepository productRepository, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
    }

    @Override
    public PageResult<ProductDTO> list(Long categoryId, Pageable pageable) {
        Page<Product> productPage;
        if (categoryId == null) {
            productPage = productRepository.findAll(pageable);
        }
        else {
            final Set<Long> categoryIds = categoryService.findSubCategoryIds(categoryId);
            categoryIds.add(categoryId);

            productPage = productRepository.findByCategoryIdIn(categoryIds, pageable);
        }
        final List<ProductDTO> datas = productPage.stream()
                .map(product -> {
                    final ProductDTO productDTO = new ProductDTO();
                    BeanUtils.copyProperties(product, productDTO);
                    return productDTO;
                }).collect(Collectors.toList());

        return new PageResult<>(productPage.getTotalElements(), productPage.getTotalPages(), datas);
    }

    @Override
    public ProductDetailDTO detail(Long productId) {
        final Product product = productRepository.findById(productId)
                .orElseThrow(()->new RuntimeException("商品不存在。"));

        if (product.getStatus().equals(ProductStatusEnum.OFF_SALE.getCode()) ||
                product.getStatus().equals(ProductStatusEnum.DELETE.getCode())) {
            throw new RuntimeException("商品下架或删除");
        }

        final ProductDetailDTO productDetailDTO = new ProductDetailDTO();
        BeanUtils.copyProperties(product, productDetailDTO);
        productDetailDTO.setStock(productDetailDTO.getStock()>100?100:productDetailDTO.getStock());
        return productDetailDTO;
    }
}
