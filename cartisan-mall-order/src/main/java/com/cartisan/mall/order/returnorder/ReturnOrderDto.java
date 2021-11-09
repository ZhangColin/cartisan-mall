package com.cartisan.mall.order.returnorder;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.lang.Long;
import java.util.Date;
import java.lang.Boolean;
import java.lang.String;
import java.lang.Integer;

@Data
public class ReturnOrderDto {
    @ApiModelProperty(value = "订单Id")
    private Long id;

    @ApiModelProperty(value = "订单Id")
    private Long orderId;

    @ApiModelProperty(value = "申请时间")
    private Date applied;

    @ApiModelProperty(value = "用户Id")
    private Long userId;

    @ApiModelProperty(value = "用户名称")
    private String username;

    @ApiModelProperty(value = "联系人")
    private String contract;

    @ApiModelProperty(value = "联系人手机")
    private String contractMobile;

    @ApiModelProperty(value = "类型（1:退货 2：退款）")
    private Integer type;

    @ApiModelProperty(value = "退款金额")
    private Integer refundAmount;

    @ApiModelProperty(value = "是否退运费")
    private Boolean refundFreight;

    @ApiModelProperty(value = "状态（0:申请 1：同意 2：驳回）")
    private Integer status;

    @ApiModelProperty(value = "处理时间")
    private Date processed;

    @ApiModelProperty(value = "退货退款原因")
    private Long returnCause;

    @ApiModelProperty(value = "凭证图片")
    private String evidence;

    @ApiModelProperty(value = "问题描述")
    private String description;

    @ApiModelProperty(value = "处理备注")
    private String remark;

}
