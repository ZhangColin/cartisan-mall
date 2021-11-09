package com.cartisan.mall.order.returnorderitem;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.lang.Long;
import java.lang.String;
import java.lang.Integer;

@Data
public class ReturnOrderItemDto {
    @ApiModelProperty(value = "订单明细Id")
    private Long id;

    @ApiModelProperty(value = "退货退款订单Id")
    private Long returnOrderId;

    @ApiModelProperty(value = "订单Id")
    private Long orderId;

    @ApiModelProperty(value = "订单明细Id")
    private Long orderItemId;

    @ApiModelProperty(value = "SPUId")
    private Long spuId;

    @ApiModelProperty(value = "SKUId")
    private Long skuId;

    @ApiModelProperty(value = "商品名称")
    private String name;

    @ApiModelProperty(value = "单价（分）")
    private Integer price;

    @ApiModelProperty(value = "数量")
    private Integer quantity;

    @ApiModelProperty(value = "重量（克）")
    private Integer weight;

    @ApiModelProperty(value = "图片")
    private String image;

}
