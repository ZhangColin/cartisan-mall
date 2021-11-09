package com.cartisan.mall.order.orderitem;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.lang.Long;
import java.lang.Boolean;
import java.lang.String;
import java.lang.Integer;

@Data
public class OrderItemDto {
    @ApiModelProperty(value = "订单明细Id")
    private Long id;

    @ApiModelProperty(value = "订单Id")
    private Long orderId;

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

    @ApiModelProperty(value = "是否退货")
    private Boolean isReturn;

}
