package com.cartisan.mall.goods.category;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author colin
 */
@Data
public class CategoryParam {
    @ApiModelProperty(value = "上级分类Id")
    private Long parentId;

    @ApiModelProperty(value = "模板Id")
    private Long templateId;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "是否显示")
    private Boolean isShow;

    @ApiModelProperty(value = "是否导航")
    private Boolean isMenu;

    @ApiModelProperty(value = "排序")
    private Integer sequence;

}
