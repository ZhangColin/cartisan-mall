package com.cartisan.mall.goods.goods.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.lang.Long;
import java.lang.String;
import java.time.LocalDateTime;

/**
 * @author colin
 */
@Data
public class SpuAuditDto {
    @ApiModelProperty(value = "SPUId")
    private Long spuId;

    @ApiModelProperty(value = "审核结果")
    private String result;

    @ApiModelProperty(value = "反馈详情")
    private String feedback;

    @ApiModelProperty(value = "审核人员")
    private String auditor;

    @ApiModelProperty(value = "审核时间")
    private LocalDateTime auditDate;

}
