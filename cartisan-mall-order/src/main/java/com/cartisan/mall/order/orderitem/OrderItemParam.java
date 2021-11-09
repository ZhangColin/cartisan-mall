package com.cartisan.mall.order.orderitem;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.lang.Long;
import java.lang.Boolean;
import java.lang.String;
import java.lang.Integer;

@Data
public class OrderItemParam {
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
