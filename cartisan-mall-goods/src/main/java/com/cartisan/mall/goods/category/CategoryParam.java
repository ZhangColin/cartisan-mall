package com.cartisan.mall.goods.category;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author colin
 */
@Data
public class CategoryParam {
    @ApiModelProperty(value = "上级分类Id")
    @NotNull(message = "上级分类不能为空")
    @CategoryVerify(allowVirtualTopCategory = true, message = "上级分类不存在")
    private Long parentId;

    @ApiModelProperty(value = "名称")
    @NotBlank(message = "分类名称不能为空")
    private String name;
}
