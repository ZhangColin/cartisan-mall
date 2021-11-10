package com.cartisan.mall.order.returnorder;

import com.cartisan.domain.AbstractEntity;
import com.cartisan.domain.AggregateRoot;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import java.lang.Long;
import java.lang.String;
import java.lang.Integer;

import static java.util.stream.Collectors.toList;

@Entity
@Table(name = "ord_return_order_items")
@Getter
public class ReturnOrderItem extends AbstractEntity implements AggregateRoot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "return_order_id")
    private Long returnOrderId;

    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "order_item_id")
    private Long orderItemId;

    @Column(name = "spu_id")
    private Long spuId;

    @Column(name = "sku_id")
    private Long skuId;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Integer price;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "weight")
    private Integer weight;

    @Column(name = "image")
    private String image;

    protected ReturnOrderItem() {}

    public ReturnOrderItem(Long returnOrderId, Long orderId, Long orderItemId, Long spuId, Long skuId, String name, Integer price, Integer quantity, Integer weight, String image) {

        this.returnOrderId = returnOrderId;
        this.orderId = orderId;
        this.orderItemId = orderItemId;
        this.spuId = spuId;
        this.skuId = skuId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.weight = weight;
        this.image = image;
    }

    public void describe(Long returnOrderId, Long orderId, Long orderItemId, Long spuId, Long skuId, String name, Integer price, Integer quantity, Integer weight, String image) {
        this.returnOrderId = returnOrderId;
        this.orderId = orderId;
        this.orderItemId = orderItemId;
        this.spuId = spuId;
        this.skuId = skuId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.weight = weight;
        this.image = image;
    }
}
