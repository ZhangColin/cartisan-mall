package com.cartisan.mall.goods.goods.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author colin
 */
@Data
public class SpuDetailDto {
    @ApiModelProperty(value = "货号")
    private String sn;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "副标题")
    private String caption;

    @ApiModelProperty(value = "品牌Id")
    private Long brandId;

    @ApiModelProperty(value = "一级分类Id")
    private Long category1Id;

    @ApiModelProperty(value = "二级分类Id")
    private Long category2Id;

    @ApiModelProperty(value = "三级分类Id")
    private Long category3Id;

    @ApiModelProperty(value = "模板Id")
    private Long templateId;

    @ApiModelProperty(value = "图片")
    private String image;

    @ApiModelProperty(value = "图片列表")
    private String images;

    @ApiModelProperty(value = "售后服务")
    private String saleService;

    @ApiModelProperty(value = "介绍")
    private String introduction;

    @ApiModelProperty(value = "规格")
    private String specificationItems;

    @ApiModelProperty(value = "参数")
    private String parameterItems;
}
