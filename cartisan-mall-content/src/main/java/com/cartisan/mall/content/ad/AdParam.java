package com.cartisan.mall.content.ad;

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
public class AdParam {
    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "广告位置")
    private String position;

    @ApiModelProperty(value = "开始时间")
    private Date start;

    @ApiModelProperty(value = "结束时间")
    private Date end;

    @ApiModelProperty(value = "状态")
    private Integer status;

    @ApiModelProperty(value = "图片")
    private String image;

    @ApiModelProperty(value = "跳转链接")
    private String url;

    @ApiModelProperty(value = "备注")
    private String remarks;

}
