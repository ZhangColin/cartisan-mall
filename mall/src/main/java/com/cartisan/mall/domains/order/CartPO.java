package com.cartisan.mall.domains.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author colin
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartPO {
    private Long productId;
    private Integer quantity;
    private Boolean productSelected;
}
