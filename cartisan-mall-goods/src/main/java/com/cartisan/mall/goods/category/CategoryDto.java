package com.cartisan.mall.goods.category;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author colin
 */
@Data
public class CategoryDto {
    @ApiModelProperty(value = "分类id")
    private Long id;

    @ApiModelProperty(value = "上级分类Id")
    private Long parentId;

    @ApiModelProperty(value = "名称")
    private String name;

}
