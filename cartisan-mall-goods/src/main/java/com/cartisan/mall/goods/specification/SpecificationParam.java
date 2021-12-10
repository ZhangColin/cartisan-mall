package com.cartisan.mall.goods.specification;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* @author colin
*/
@Data
public class SpecificationParam {
    @ApiModelProperty(value = "分类ID", required = true)
    @NotNull(message = "分类ID不能为空")
    private Long categoryId = 0L;

    @ApiModelProperty(value = "规格名称", required = true)
    @NotBlank(message = "规格名称不能为空")
    @Length(max=32, message = "规格名称最大长度不能超过32")
    private String name;

    @ApiModelProperty(value = "可选值列表")
    private List<String> valueOptions;

    @ApiModelProperty(value = "是否支持手动新增", required = true)
    @NotNull(message = "是否支持手动新增不能为空")
    private Boolean manualAdd = false;

    @ApiModelProperty(value = "是否启用", required = true)
    @NotNull(message = "是否启用不能为空")
    private Boolean enable = true;

    @ApiModelProperty(value = "排序", required = true)
    @NotNull(message = "排序不能为空")
    private Integer sequence = 0;

}
