package com.cartisan.mall.domains.product;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author colin
 */
@Data
public class ProductDTO {
    private Long id;

    private Long categoryId;

    private String name;

    private String mainImage;

    private BigDecimal price;

    private Integer status;
}
