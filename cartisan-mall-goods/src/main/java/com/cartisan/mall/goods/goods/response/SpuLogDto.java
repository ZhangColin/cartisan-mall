package com.cartisan.mall.goods.goods.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author colin
 */
@Data
public class SpuLogDto {
    @ApiModelProperty(value = "SPUId")
    private Long spuId;

    @ApiModelProperty(value = "日志内容")
    private String content;

    @ApiModelProperty(value = "操作人员")
    private String operator;

    @ApiModelProperty(value = "操作时间")
    private LocalDateTime operateDate;

}
