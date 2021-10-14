package com.cartisan.mall.goods.category;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.lang.Long;
import java.lang.String;
import java.lang.Integer;

@Data
public class CategoryParam {
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
