package com.cartisan.mall.goods.brand;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @author colin
 */
@Data
public class BrandParam {
    @ApiModelProperty(value = "品牌名称", required = true)
    @NotBlank(message = "品牌名称不能为空")
    private String name;

    @ApiModelProperty(value = "品牌Logo地址")
    @NotBlank(message = "品牌Logo地址不能为空")
    @URL(message = "品牌Logo必须是一个合法的Url地址")
    private String logo;

    @ApiModelProperty(value = "品牌描述")
    private String description;

    @ApiModelProperty(value = "品牌的首字母")
    @NotBlank(message = "品牌的首字母不能为空")
    @Pattern(regexp = "^[a-zA-Z]$", message = "品牌的首字母必须是一个字母")
    private String firstLetter;

    @ApiModelProperty(value = "排序")
    @NotNull(message = "排序不能为空")
    @Min(value = 0, message = "排序必须大于等于0")
    private Integer sequence;
}
