package com.cartisan.mall.content.activity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.lang.Long;
import java.util.Date;
import java.lang.String;
import java.lang.Integer;

@Data
public class ActivityDto {
    @ApiModelProperty(value = "主键Id")
    private Long id;

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
