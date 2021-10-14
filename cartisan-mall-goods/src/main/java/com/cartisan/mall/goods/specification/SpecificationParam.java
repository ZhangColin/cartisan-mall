package com.cartisan.mall.goods.specification;

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
public class SpecificationParam {
    @ApiModelProperty(value = "模板Id")
    private Long templateId;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "选项")
    private String options;

    @ApiModelProperty(value = "排序")
    private Integer sequence;

}
