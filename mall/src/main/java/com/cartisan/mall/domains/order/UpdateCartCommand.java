package com.cartisan.mall.domains.order;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author colin
 */
@Data
public class UpdateCartCommand {
    @NotNull
    private Long productId;
    private Integer quantity;
    private Boolean selected;
    private Long userId;
}
