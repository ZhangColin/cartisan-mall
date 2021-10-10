package com.cartisan.pay.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author colin
 */
@Data
@AllArgsConstructor
public class PaymentResponse {
    private Long orderId;
    private String outTradeNo;
    private double orderAmount;
}
