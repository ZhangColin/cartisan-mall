package com.cartisan.mall.domains.product;

import com.cartisan.dtos.PageResult;
import org.springframework.data.domain.Pageable;

/**
 * @author colin
 */
public interface ProductService {
    PageResult<ProductDTO> list(Long categoryId, Pageable pageable);

    ProductDetailDTO detail(Long productId);
}
