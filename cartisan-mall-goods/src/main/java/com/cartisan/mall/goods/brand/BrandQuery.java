package com.cartisan.mall.goods.brand;

import com.cartisan.repository.Condition;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author colin
 */
@Data
public class BrandQuery {
    @ApiModelProperty(value = "品牌名称")
    @Condition(propName = "name", type = Condition.Type.INNER_LIKE)
    private String name;

    @ApiModelProperty(value = "品牌的首字母")
    @Condition(propName = "firstLetter", type = Condition.Type.EQUAL)
    private String firstLetter;
}
