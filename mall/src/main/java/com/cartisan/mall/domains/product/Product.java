package com.cartisan.mall.domains.product;

import com.cartisan.domain.AbstractEntity;
import com.cartisan.domain.AggregateRoot;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @author colin
 */
@Entity
@Table(name = "mall_product")
@Data
@ToString
public class Product extends AbstractEntity implements AggregateRoot {
    /**
     * 商品 Id
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 分类 Id
     */
    @Column(name = "category_id")
    private Long categoryId;

    /**
     * 商品名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 商品副标题
     */
    @Column(name = "subtitle")
    private String subTitle;

    /**
     * 产品主图，url 相对地址
     */
    @Column(name = "main_image")
    private String mainImage;

    /**
     * 图片地址，Json 格式，扩展用
     */
    @Column(name = "sub_images")
    private String subImages;

    /**
     * 商品详情
     */
    @Column(name = "detail")
    private String detail;

    /**
     * 价格，单位（元），保留两位小数
     */
    @Column(name = "price")
    private BigDecimal price;

    /**
     * 库存数量
     */
    @Column(name = "stock")
    private Integer stock;

    /**
     * 商品状态
     * 1 - 在售
     * 2 - 下架
     * 3 - 已删除
     */
    @Column(name = "status")
    private Integer status;
}
