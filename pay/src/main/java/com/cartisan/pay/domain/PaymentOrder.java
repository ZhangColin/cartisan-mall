package com.cartisan.pay.domain;

import com.cartisan.domain.AbstractEntity;
import com.cartisan.domain.AggregateRoot;
import com.cartisan.pay.domain.primitive.PaymentPlatform;
import com.cartisan.pay.domain.primitive.PaymentStatus;
import com.cartisan.pay.domain.primitive.PaymentType;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * @author colin
 */
@Entity
@Table(name = "mall_pay_info")
@Getter
@ToString
public class PaymentOrder extends AbstractEntity implements AggregateRoot {
    @Id
    @Column(name = "id")
    private Long paymentOrderNo;

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
     */
    @Column(name = "pay_platform")
    private PaymentPlatform paymentPlatform;

    /**
     * 支付类型
     */
    @Column(name = "pay_platform")
    private PaymentType paymentType;

    /**
     * 平台支付流水号
     */
    @Column(name = "platform_number")
    private String platformNumber;

    /**
     * 平台支付状态
     */
    @Column(name = "platform_status")
    private String platformStatus;

    /**
     * 支付金额
     */
    @Column(name = "pay_amount")
    private BigDecimal amount;

    /**
     * 支付状态
     */
    private PaymentStatus paymentStatus;

    public PaymentOrder(Long paymentOrderNo, Long orderNo, Long userId, BigDecimal amount, PaymentType paymentType) {
        this.paymentOrderNo = paymentOrderNo;
        this.orderNo = orderNo;
        this.userId = userId;
        this.amount = amount;
        this.paymentType = paymentType;
        this.paymentPlatform = paymentType.getPlatform();
        this.paymentStatus = PaymentStatus.NOTPAY;
    }

    protected PaymentOrder() {
    }

    public void paid(String platformNumber, String platformStatus) {
        this.platformNumber = platformNumber;
        this.platformStatus = platformStatus;
        this.paymentStatus = PaymentStatus.SUCCESS;
    }
}
