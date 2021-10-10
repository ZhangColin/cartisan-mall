package com.cartisan.mall.domains.order;

import com.cartisan.domain.AbstractEntity;
import com.cartisan.domain.AggregateRoot;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author colin
 */
@Entity
@Table(name = "mall_order")
@Data
@ToString
public class Order extends AbstractEntity implements AggregateRoot {
    /**
     * 订单Id
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 订单号
     */
    @Column(name = "order_no")
    private Long orderNo;

    /**
     * 用户Id
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     *
     */
    @Column(name = "shipping_id")
    private Long shippingId;

    /**
     * 实际付款金额，单位（元），保留两位小数
     */
    @Column(name = "payment")
    private BigDecimal payment;

    /**
     * 支付类型
     * 1 - 在线支付
     */
    @Column(name = "payment_type")
    private Integer paymentType;

    /**
     * 运费，单位（元）
     */
    @Column(name = "postage")
    private BigDecimal postage;

    /**
     * 订单状态
     * 0 - 已取消
     * 10 - 未付款
     * 20 - 已付款
     * 40 - 已发货
     * 50 - 交易成功
     * 60 - 交易关闭
     */
    @Column(name = "status")
    private Integer status;

    /**
     * 支付时间
     */
    @Column(name = "payment_time")
    private LocalDateTime paymentTime;

    /**
     * 发货时间
     */
    @Column(name = "send_time")
    private LocalDateTime sendTime;

    /**
     * 交易完成时间
     */
    @Column(name = "end_time")
    private LocalDateTime endTime;

    /**
     * 交易关闭时间
     */
    @Column(name = "close_time")
    private LocalDateTime closeTime;
}
