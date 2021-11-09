package com.cartisan.mall.order.order;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.lang.Long;
import java.util.Date;
import java.lang.Boolean;
import java.lang.String;
import java.lang.Integer;

@Data
public class OrderParam {
    @ApiModelProperty(value = "总金额")
    private Integer totalAmount;

    @ApiModelProperty(value = "优惠金额")
    private Integer discountAmount;

    @ApiModelProperty(value = "运费")
    private Integer freight;

    @ApiModelProperty(value = "实付金额")
    private Integer actualAmount;

    @ApiModelProperty(value = "支付类型（0:货到付款 1：在线支付）")
    private Integer payType;

    @ApiModelProperty(value = "下单时间")
    private Date placed;

    @ApiModelProperty(value = "支付时间")
    private Date paid;

    @ApiModelProperty(value = "发货时间")
    private Date delivered;

    @ApiModelProperty(value = "交易完成时间")
    private Date completed;

    @ApiModelProperty(value = "交易关闭时间")
    private Date closed;

    @ApiModelProperty(value = "物流名称")
    private String shippingName;

    @ApiModelProperty(value = "物流单号")
    private String shippingCode;

    @ApiModelProperty(value = "用户名称")
    private String username;

    @ApiModelProperty(value = "买家留言")
    private String buyerMessage;

    @ApiModelProperty(value = "是否评价")
    private Boolean buyerRate;

    @ApiModelProperty(value = "收货人")
    private String receiverContact;

    @ApiModelProperty(value = "收货人手机")
    private String receiverMobile;

    @ApiModelProperty(value = "收货人地址")
    private String receiverAddress;

    @ApiModelProperty(value = "订单来源（1:Web 2：APP 3：微信公众号 4：微信小程序 5：H5）")
    private Integer sourceType;

    @ApiModelProperty(value = "订单状态")
    private Integer orderStatus;

    @ApiModelProperty(value = "支付状态")
    private Integer payStatus;

}
