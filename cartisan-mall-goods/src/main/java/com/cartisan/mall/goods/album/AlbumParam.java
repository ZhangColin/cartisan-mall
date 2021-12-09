package com.cartisan.mall.goods.album;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author colin
 */
@Data
public class AlbumParam {
    @ApiModelProperty(value = "标题", required = true)
    @NotBlank(message = "标题不能为空")
    @Length(max=64, message = "标题最大长度不能超过64")
    private String title;

    @ApiModelProperty(value = "封面")
    @Length(max=256, message = "封面最大长度不能超过256")
    private String coverImage;

    @ApiModelProperty(value = "描述")
    @Length(max=256, message = "描述最大长度不能超过256")
    private String description;

    @ApiModelProperty(value = "排序", required = true)
    @NotNull(message = "排序不能为空")
    private Integer sequence = 0;

}
