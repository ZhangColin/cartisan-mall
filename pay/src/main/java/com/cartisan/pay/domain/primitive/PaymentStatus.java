package com.cartisan.pay.domain.primitive;

import lombok.Getter;

/**
 * @author colin
 */
@Getter
public enum PaymentStatus {
    /**
     * 支付成功
     */
    SUCCESS(0, "支付成功"),
    /**
     * 转入退款
     */
    REFUND(1, "转入退款"),
    /**
     * 未支付
     */
    NOTPAY(2, "未支付"),
    /**
     * 已关闭
     */
    CLOSED(3, "已关闭"),
    /**
     * 已撤销（刷卡支付）
     */
    REVOKED(4, "已撤销（刷卡支付）"),
    /**
     * 用户支付中
     */
    USERPAYING(5, "用户支付中"),
    /**
     * 支付失败
     */
    PAYERROR(6, "支付失败"),
    /**
     * 未知状态
     */
    UNKNOW(7, "未知状态"),
    ;

    private final Integer code;
    private final String description;

    PaymentStatus(Integer code, String description) {
        this.code = code;
        this.description = description;
    }
}
