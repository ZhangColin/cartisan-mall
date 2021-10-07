package com.cartisan.mall.domains.product;

import lombok.Getter;

/**
 * @author colin
 */
@Getter
public enum ProductStatusEnum {
    ON_SALE(1, "在售"),
    OFF_SALE(2, "下架"),
    DELETE(3, "删除");

    private final Integer code;
    private final String description;

    ProductStatusEnum(Integer code, String description) {
        this.code = code;
        this.description = description;
    }
}
