package com.cartisan.mall.domains.order;

import lombok.Data;

/**
 * @author colin
 */
@Data
public class PlaceOrderCommand {
    private Long userId;
    private Long shippingId;
}
