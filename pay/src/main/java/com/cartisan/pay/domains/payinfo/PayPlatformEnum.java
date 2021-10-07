package com.cartisan.pay.domains.payinfo;

import com.lly835.bestpay.enums.BestPayTypeEnum;
import lombok.Getter;

/**
 * @author colin
 */
@Getter
public enum PayPlatformEnum {
    // 1-支付宝
    ALIPAY(1),
    // 2-微信
    WX(2);

    Integer code;

    PayPlatformEnum(Integer code) {
        this.code = code;
    }

    public static PayPlatformEnum getByBestPayTypeEnum(BestPayTypeEnum bestPayTypeEnum) {
        for (PayPlatformEnum payPlatformEnum : PayPlatformEnum.values()) {
            if (bestPayTypeEnum.getPlatform().name().equals(payPlatformEnum.name())) {
                return payPlatformEnum;
            }
        }

        throw new RuntimeException("错误的支付平台：" + bestPayTypeEnum.name());
    }
}
