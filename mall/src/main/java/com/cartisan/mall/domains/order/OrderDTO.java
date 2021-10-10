package com.cartisan.mall.domains.order;

import com.cartisan.mall.domains.shipping.Shipping;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author colin
 */
@Data
public class OrderDTO{

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

    private List<OrderItemDTO> items;

    private Long shippingId;
    private Shipping shipping;
}
