package com.cartisan.mall.domains.product;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author colin
 */
@Data
public class ProductDetailDTO {
    private Long id;
    private Long categoryId;
    private String name;
    private String subTitle;
    private String mainImage;
    private String subImages;
    private String detail;
    private BigDecimal price;
    private Integer stock;
    private Integer status;
}
