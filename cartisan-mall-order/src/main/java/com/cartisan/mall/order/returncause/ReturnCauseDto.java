package com.cartisan.mall.order.returncause;

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
public class ReturnCauseDto {
    @ApiModelProperty(value = "退货原因Id")
    private Long id;

    @ApiModelProperty(value = "原因")
    private String cause;

    @ApiModelProperty(value = "是否启用")
    private Boolean enable;

    @ApiModelProperty(value = "排序")
    private Integer sequence;

}
