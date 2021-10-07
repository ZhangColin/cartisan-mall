package com.cartisan.mall.domains.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author colin
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartProductDTO {
    private Long productId;
    private Integer quantity;
    private String productName;
    private String productSubtitle;
    private String productMainImage;
    private BigDecimal productPrice;
    private Integer productStatus;
    private BigDecimal productTotalPrice;
    private Integer productStock;
    private Boolean productSelected;
}
