package com.cartisan.mall.goods.goods.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.lang.Long;
import java.lang.String;
import java.lang.Integer;

/**
 * @author colin
 */
@Data
public class SkuDto {
    @ApiModelProperty(value = "SKUId")
    private Long id;

    @ApiModelProperty(value = "SPUId")
    private Long spuId;

    @ApiModelProperty(value = "商品条码")
    private String sn;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "分类Id")
    private String categoryId;

    @ApiModelProperty(value = "分类名称")
    private String categoryName;

    @ApiModelProperty(value = "规格")
    private String specification;

    @ApiModelProperty(value = "价格（分）")
    private Integer price;

    @ApiModelProperty(value = "重量（克）")
    private Integer weight;

    @ApiModelProperty(value = "库存数量")
    private Integer quantity;

    @ApiModelProperty(value = "库存预警数量")
    private Integer alertQuantity;

    @ApiModelProperty(value = "商品图片")
    private String image;

    @ApiModelProperty(value = "商品图片列表")
    private String images;

}
