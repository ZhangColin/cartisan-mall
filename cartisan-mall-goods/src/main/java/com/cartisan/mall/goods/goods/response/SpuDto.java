package com.cartisan.mall.goods.goods.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.lang.Long;
import java.lang.Boolean;
import java.lang.String;
import java.lang.Integer;

/**
 * @author colin
 */
@Data
public class SpuDto {
    @ApiModelProperty(value = "SPUId")
    private Long id;

    @ApiModelProperty(value = "货号")
    private String sn;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "品牌Id")
    private Long brandId;

    @ApiModelProperty(value = "品牌名称")
    private String brandName;

    @ApiModelProperty(value = "三级分类Id")
    private Long category3Id;

    @ApiModelProperty(value = "图片")
    private String image;

    @ApiModelProperty(value = "是否上架")
    private Boolean isMarketable;

    @ApiModelProperty(value = "审核状态")
    private Integer auditStatus;
}
