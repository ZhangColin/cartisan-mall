package com.cartisan.mall.order.returncause;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author colin
 */
@Data
public class ReturnCauseParam {
    @ApiModelProperty(value = "原因")
    private String cause;

    @ApiModelProperty(value = "是否启用")
    private Boolean enable;

    @ApiModelProperty(value = "排序")
    private Integer sequence;

}
