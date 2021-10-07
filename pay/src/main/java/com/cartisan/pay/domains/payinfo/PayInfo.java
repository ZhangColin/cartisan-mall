package com.cartisan.pay.domains.payinfo;

import com.cartisan.domains.AggregateRoot;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author colin
 */
@Entity
@Table(name = "mall_pay_info")
@Getter
@ToString
public class PayInfo implements AggregateRoot, Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户 Id
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 订单号
     */
    @Column(name = "order_no")
    private Long orderNo;

    /**
     * 支付平台
     * 1 - 支付宝
     * 2 - 微信
     */
    @Column(name = "pay_platform")
    private Integer payPlatform;

    /**
     * 平台支付流水号
     */
    @Column(name = "platform_number")
    @Setter
    private String platformNumber;

    /**
     * 平台支付状态
     */
    @Column(name = "platform_status")
    @Setter
    private String platformStatus;

    /**
     * 支付金额
     */
    @Column(name = "pay_amount")
    private BigDecimal payAmount;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private LocalDateTime updateTime;

    public PayInfo(Long orderNo, Integer payPlatform, String platformStatus, BigDecimal payAmount) {
        this.orderNo = orderNo;
        this.payPlatform = payPlatform;
        this.platformNumber = platformNumber;
        this.platformStatus = platformStatus;
        this.payAmount = payAmount;
    }

    protected PayInfo() {
    }
}
