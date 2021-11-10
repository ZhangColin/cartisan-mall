package com.cartisan.mall.order.order;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class OrderDto {
    @ApiModelProperty(value = "订单Id")
    private Long id;

    @ApiModelProperty(value = "总金额")
    private Integer totalAmount;

    @ApiModelProperty(value = "支付类型（0:货到付款 1：在线支付）")
    private Integer payType;

    @ApiModelProperty(value = "用户名称")
    private String username;

    @ApiModelProperty(value = "订单来源（1:Web 2：APP 3：微信公众号 4：微信小程序 5：H5）")
    private Integer sourceType;

    @ApiModelProperty(value = "订单状态")
    private Integer orderStatus;

    @ApiModelProperty(value = "支付状态")
    private Integer payStatus;

}
