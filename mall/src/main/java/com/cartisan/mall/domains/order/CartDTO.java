package com.cartisan.mall.domains.order;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author colin
 */
@Data
public class CartDTO {
    private List<CartProductDTO> cartProductDTOList;

    private Boolean selectedAll;

    private BigDecimal cartTotalPrice;

    private Integer cartTotalQuantity;
}
