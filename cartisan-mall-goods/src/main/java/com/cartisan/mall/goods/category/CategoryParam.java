package com.cartisan.mall.goods.category;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author colin
 */
@Data
public class CategoryParam {
    @ApiModelProperty(value = "上级分类ID", required = true)
    @NotNull(message = "上级分类ID不能为空")
    @CategoryVerify(allowVirtualTopCategory = true, message = "上级分类不存在")
    private Long parentId;

    @ApiModelProperty(value = "名称", required = true)
    @NotBlank(message = "名称不能为空")
    @Length(max=64, message = "名称最大长度不能超过64")
    private String name;

    @ApiModelProperty(value = "图标")
    @Length(max=256, message = "图标最大长度不能超过256")
    private String icon;
}
