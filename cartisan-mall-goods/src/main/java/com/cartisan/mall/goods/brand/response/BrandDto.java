package com.cartisan.mall.goods.brand.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author colin
 */
@Data
public class BrandDto {
    @ApiModelProperty(value = "品牌id")
    private Long id;

    @ApiModelProperty(value = "品牌名称")
    private String name;

    @ApiModelProperty(value = "品牌图片地址")
    private String logo;

    @ApiModelProperty(value = "品牌描述")
    private String description;

    @ApiModelProperty(value = "品牌的首字母")
    private String firstLetter;

    @ApiModelProperty(value = "排序")
    private Integer sequence;

}
