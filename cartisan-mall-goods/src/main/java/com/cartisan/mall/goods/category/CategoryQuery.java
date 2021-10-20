package com.cartisan.mall.goods.category;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author colin
 */
@Data
public class CategoryQuery {
    @ApiModelProperty(value = "上级分类")
    private Long parentId = 0L;
}
