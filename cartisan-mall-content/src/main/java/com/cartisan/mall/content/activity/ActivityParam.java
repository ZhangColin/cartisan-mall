package com.cartisan.mall.content.activity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author colin
 */
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
