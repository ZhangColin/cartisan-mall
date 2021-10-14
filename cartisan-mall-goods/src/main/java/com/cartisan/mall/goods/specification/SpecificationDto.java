package com.cartisan.mall.goods.specification;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.lang.Long;
import java.lang.String;
import java.lang.Integer;

@Data
public class SpecificationDto {
    @ApiModelProperty(value = "规格id")
    private Long id;

    @ApiModelProperty(value = "模板Id")
    private Long templateId;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "选项")
    private String options;

    @ApiModelProperty(value = "排序")
    private Integer sequence;

}
