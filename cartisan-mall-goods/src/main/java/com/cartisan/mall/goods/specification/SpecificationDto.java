package com.cartisan.mall.goods.specification;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author colin
 */
@Data
public class SpecificationDto {
    @ApiModelProperty(value = "规格ID")
    private Long id;

    @ApiModelProperty(value = "分类ID")
    private Long categoryId;

    @ApiModelProperty(value = "规格名称")
    private String name;

    @ApiModelProperty(value = "可选值列表")
    private List<String> valueOptions;

    @ApiModelProperty(value = "是否支持手动新增")
    private Boolean manualAdd;

    @ApiModelProperty(value = "是否启用")
    private Boolean enable;

    @ApiModelProperty(value = "排序")
    private Integer sequence;

}
