package com.cartisan.mall.goods.brand.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author colin
 */
@Data
public class BrandDto {
    @ApiModelProperty(value = "品牌ID")
    private Long id;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "Logo地址")
    private String logo;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "首字母")
    private String firstLetter;

    @ApiModelProperty(value = "排序")
    private Integer sequence;

}
