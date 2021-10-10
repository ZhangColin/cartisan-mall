package com.cartisan.mall.domains.order;

import lombok.Getter;

/**
 * @author colin
 */
@Getter
public enum PaymentType {
    PAY_ONLINE(1);

    private final Integer code;

    PaymentType(Integer code) {
        this.code = code;
    }
}
