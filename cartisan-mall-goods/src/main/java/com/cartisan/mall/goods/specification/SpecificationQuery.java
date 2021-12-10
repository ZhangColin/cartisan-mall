package com.cartisan.mall.goods.specification;

import com.cartisan.repository.Condition;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author colin
 */
@Data
public class SpecificationQuery {
    @ApiModelProperty(value = "商品分类")
    @Condition(propName = "categoryId", type = Condition.Type.EQUAL)
    private Long categoryId;
}
