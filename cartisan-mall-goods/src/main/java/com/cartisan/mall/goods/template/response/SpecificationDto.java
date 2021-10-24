package com.cartisan.mall.goods.template.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author colin
 */
@Data
public class SpecificationDto {
    @ApiModelProperty(value = "规格Id")
    private Long id;

    @ApiModelProperty(value = "模板Id")
    private Long templateId;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "选项")
    private List<String> options;

    @ApiModelProperty(value = "排序")
    private Integer sequence;

}
