package com.cartisan.mall.order.orderitem;

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
import java.lang.Boolean;
import java.lang.String;
import java.lang.Integer;

import static java.util.stream.Collectors.toList;

@Entity
@Table(name = "ord_order_items")
@Getter
public class OrderItem extends AbstractEntity implements AggregateRoot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "order_id")
    private Long orderId;

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

    @Column(name = "is_return")
    private Boolean isReturn;

    protected OrderItem() {}

    public OrderItem(Long orderId, Long spuId, Long skuId, String name, Integer price, Integer quantity, Integer weight, String image, Boolean isReturn) {

        this.orderId = orderId;
        this.spuId = spuId;
        this.skuId = skuId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.weight = weight;
        this.image = image;
        this.isReturn = isReturn;
    }

    public void describe(Long orderId, Long spuId, Long skuId, String name, Integer price, Integer quantity, Integer weight, String image, Boolean isReturn) {
        this.orderId = orderId;
        this.spuId = spuId;
        this.skuId = skuId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.weight = weight;
        this.image = image;
        this.isReturn = isReturn;
    }
}
