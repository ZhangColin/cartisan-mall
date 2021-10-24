package com.cartisan.mall.goods.template.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author colin
 */
@Data
public class SpecificationParam {
    @ApiModelProperty(value = "模板Id")
    private Long templateId;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "选项")
    private List<String> options;

    @ApiModelProperty(value = "排序")
    private Integer sequence;

}
