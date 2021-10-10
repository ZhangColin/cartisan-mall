package com.cartisan.mall.domains.order;

import com.cartisan.mall.domains.shipping.Shipping;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author colin
 */
@Data
public class OrderInfo {

    private Long orderNo;
    private BigDecimal payment;
    private Integer paymentType;
    private BigDecimal postage;
    private Integer status;
    private LocalDateTime paymentTime;
    private LocalDateTime sendTime;
    private LocalDateTime endTime;
    private LocalDateTime closeTime;
    private LocalDateTime createTime;

    private Long shippingId;
    private Shipping shipping;
}
