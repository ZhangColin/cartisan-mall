package com.cartisan.mall.goods.goods.request;

import com.cartisan.repository.Condition;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author colin
 */
@Data
public class SkuQuery {
    @ApiModelProperty(value = "SPU")
    @Condition(propName = "spuId", type = Condition.Type.EQUAL)
    private Long spuId;
}
