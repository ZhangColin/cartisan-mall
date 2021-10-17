package com.cartisan.mall.domains.product;

import com.cartisan.dto.PageResult;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.cartisan.response.ResponseUtil.success;

/**
 * @author colin
 */
@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<PageResult<ProductDTO>> list(Long categoryId, @PageableDefault Pageable pageable) {
        return success(productService.list(categoryId, pageable));
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDetailDTO> getProductId(@PathVariable Long productId) {
        return success(productService.detail(productId));
    }
}
