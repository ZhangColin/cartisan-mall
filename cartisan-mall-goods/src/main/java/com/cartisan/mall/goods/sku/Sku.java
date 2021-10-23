package com.cartisan.mall.goods.sku;

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
@Table(name = "gds_skus")
@Getter
public class Sku extends AbstractEntity implements AggregateRoot {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "spu_id")
    private Long spuId;

    @Column(name = "sn")
    private String sn;

    @Column(name = "name")
    private String name;

    @Column(name = "specification")
    private String specification;

    @Column(name = "price")
    private Integer price;

    @Column(name = "weight")
    private Integer weight;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "alert_quantity")
    private Integer alertQuantity;

    @Column(name = "image")
    private String image;

    @Column(name = "images")
    private String images;

    protected Sku() {}

    public Sku(Long id, Long spuId, String sn, String name, String specification, Integer price, Integer weight, Integer quantity, Integer alertQuantity, String image, String images) {
        this.id = id;
        this.spuId = spuId;
        this.sn = sn;
        this.name = name;
        this.specification = specification;
        this.price = price;
        this.weight = weight;
        this.quantity = quantity;
        this.alertQuantity = alertQuantity;
        this.image = image;
        this.images = images;
    }

    public void describe(Long spuId, String sn, String name, String specification, Integer price, Integer weight, Integer quantity, Integer alertQuantity, String image, String images) {
        this.spuId = spuId;
        this.sn = sn;
        this.name = name;
        this.specification = specification;
        this.price = price;
        this.weight = weight;
        this.quantity = quantity;
        this.alertQuantity = alertQuantity;
        this.image = image;
        this.images = images;
    }
}
