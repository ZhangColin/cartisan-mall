package com.cartisan.mall.domains.order;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author colin
 */
@Data
public class DeleteCartCommand {
    @NotNull
    private Long productId;
    private Long userId;
}
