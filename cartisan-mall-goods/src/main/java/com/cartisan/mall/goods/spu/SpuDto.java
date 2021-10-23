package com.cartisan.mall.goods.spu;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.lang.Long;
import java.lang.Boolean;
import java.lang.String;
import java.lang.Integer;

@Data
public class SpuDto {
    @ApiModelProperty(value = "SPUId")
    private Long id;

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

    @ApiModelProperty(value = "是否上架")
    private Boolean isMarketable;

    @ApiModelProperty(value = "是否启用规格")
    private Boolean enableSpecification;

    @ApiModelProperty(value = "审核状态")
    private Integer auditStatus;

}
