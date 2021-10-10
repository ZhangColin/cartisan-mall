package com.cartisan.pay.domain.primitive;

import lombok.Getter;

/**
 * 支付平台
 * @author colin
 */
@Getter
public enum PaymentPlatform {
    /**
     * 支付宝
     */
    ALIPAY("alipay", "支付宝"),

    /**
     * 微信
     */
    WX("wx", "微信"),
    ;

    private final String code;
    private final String name;

    PaymentPlatform(String code, String name) {
        this.code = code;
        this.name = name;
    }
}
