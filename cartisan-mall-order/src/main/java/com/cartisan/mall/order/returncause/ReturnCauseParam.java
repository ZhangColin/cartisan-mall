package com.cartisan.mall.order.returncause;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.lang.Long;
import java.lang.Boolean;
import java.lang.String;
import java.lang.Integer;

@Data
public class ReturnCauseParam {
    @ApiModelProperty(value = "原因")
    private String cause;

    @ApiModelProperty(value = "是否启用")
    private Boolean enable;

    @ApiModelProperty(value = "排序")
    private Integer sequence;

}
