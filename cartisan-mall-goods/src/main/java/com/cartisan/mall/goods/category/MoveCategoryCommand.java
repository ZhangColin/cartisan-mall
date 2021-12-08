package com.cartisan.mall.goods.category;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author colin
 */
@Data
public class MoveCategoryCommand {
    @ApiModelProperty(value = "分类Id", required = true)
    @NotNull(message = "分类Id不能为空")
    @CategoryVerify
    private Long id;

    @ApiModelProperty(value = "上级分类ID", required = true)
    @NotNull(message = "上级分类ID不能为空")
    @CategoryVerify(allowVirtualTopCategory = true, message = "上级分类不存在")
    private Long parentId;

    @ApiModelProperty(value = "排序", required = true)
    @NotNull(message = "排序不能为空")
    @Min(value = 0, message = "排序必须大于等于0")
    private Integer sequence;
}
