package com.cartisan.mall.goods.brand.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
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
    @ApiModelProperty(value = "名称", required = true)
    @NotBlank(message = "名称不能为空")
    @Length(max=64, message = "名称最大长度不能超过64")
    private String name;

    @ApiModelProperty(value = "Logo地址")
    @Length(max=256, message = "Logo地址最大长度不能超过256")
    @URL(message = "品牌Logo必须是一个合法的Url地址")
    private String logo;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "首字母", required = true)
    @NotBlank(message = "首字母不能为空")
    @Pattern(regexp = "^[a-zA-Z]$", message = "品牌的首字母必须是一个字母")
    private String firstLetter;

    @ApiModelProperty(value = "排序", required = true)
    @NotNull(message = "排序不能为空")
    @Min(value = 0, message = "排序必须大于等于0")
    private Integer sequence;
}
