package com.cartisan.mall.goods.category;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.lang.Long;
import java.lang.String;
import java.lang.Integer;

@Data
public class CategoryDto {
    @ApiModelProperty(value = "分类id")
    private Long id;

    @ApiModelProperty(value = "上级分类Id")
    private Long parentId;

    @ApiModelProperty(value = "模板Id")
    private Long templateId;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "是否显示")
    private String isShow;

    @ApiModelProperty(value = "是否导航")
    private String isMenu;

    @ApiModelProperty(value = "排序")
    private Integer sequence;

}
