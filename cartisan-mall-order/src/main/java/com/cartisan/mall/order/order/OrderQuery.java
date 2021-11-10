package com.cartisan.mall.order.order;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author colin
 */
@Data
public class OrderQuery {
    @ApiModelProperty(value = "订单编号/商品货号")
    private String orderNo;

    @ApiModelProperty(value = "状态")
    private Integer status;

    @ApiModelProperty(value = "收货人姓名/手机号码")
    private String receiver;

    @ApiModelProperty(value = "下单时间")
    private String placedRange;
}
