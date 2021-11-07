package com.cartisan.mall.goods.goods.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.lang.Long;
import java.lang.String;

/**
 * @author colin
 */
@Data
public class SpuLogDto {
    @ApiModelProperty(value = "日志Id")
    private Long id;

    @ApiModelProperty(value = "SPUId")
    private Long spuId;

    @ApiModelProperty(value = "日志内容")
    private String content;

    @ApiModelProperty(value = "操作人员")
    private String operator;

}
