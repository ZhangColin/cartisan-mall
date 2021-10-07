package com.cartisan.mall.domains.order;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author colin
 */
@Data
public class AddCartCommand {
    @NotNull
    private Long productId;
    private Boolean selected = true;
    private Long userId;
}
