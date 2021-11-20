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

    @ApiModelProperty(value = "是否显示")
    private Boolean isShow;

    @ApiModelProperty(value = "是否导航")
    private Boolean isMenu;

}
