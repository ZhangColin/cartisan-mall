package com.cartisan.mall.domains.user;

import lombok.Getter;

/**
 * @author colin
 */
@Getter
public enum RoleEnum {
    ADMIN(0),
    CUSTOMER(1);

    private final Integer code;

    RoleEnum(Integer code) {
        this.code = code;
    }
}
