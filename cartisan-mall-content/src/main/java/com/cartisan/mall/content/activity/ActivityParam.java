package com.cartisan.mall.content.activity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.lang.Long;
import java.util.Date;
import java.lang.String;
import java.lang.Integer;

@Data
public class ActivityParam {
    @ApiModelProperty(value = "活动标题")
    private String title;

    @ApiModelProperty(value = "开始时间")
    private Date start;

    @ApiModelProperty(value = "结束时间")
    private Date end;

    @ApiModelProperty(value = "活动内容")
    private String content;

    @ApiModelProperty(value = "状态")
    private Integer status;

}
