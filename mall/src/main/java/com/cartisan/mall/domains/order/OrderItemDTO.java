package com.cartisan.mall.domains.order;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author colin
 */
@Data
public class OrderItemDTO {
    private Long orderNo;
    private Long productId;
    private String productName;
    private String productImage;
    private BigDecimal currentUnitPrice;
    private Integer quantity;
    private BigDecimal totalPrice;
    private LocalDateTime createTime;
}
