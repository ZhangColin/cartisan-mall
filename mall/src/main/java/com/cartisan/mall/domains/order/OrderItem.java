package com.cartisan.mall.domains.order;

import com.cartisan.domains.AbstractEntity;
import com.cartisan.domains.AggregateRoot;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author colin
 */
@Entity
@Table(name = "mall_order_item")
@Getter
@ToString
public class OrderItem extends AbstractEntity implements AggregateRoot {
    /**
     * 订单项 Id
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户 Id
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 订单号
     */
    @Column(name = "order_no")
    private Long orderNo;

    /**
     * 商品 Id
     */
    @Column(name = "product_id")
    private Long productId;

    /**
     * 商品名称
     */
    @Column(name = "product_name")
    private String productName;

    /**
     * 商品图片地址
     */
    @Column(name = "product_image")
    private String productImage;

    /**
     * 生成订单时的商品单价，单位（元），保留两位小数
     */
    @Column(name = "current_unit_price")
    private BigDecimal currentUnitPrice;

    /**
     * 商品数量
     */
    @Column(name = "quantity")
    private Integer quantity;

    /**
     * 商品总价，单元（元），保留两位小数
     */
    @Column(name = "total_price")
    private BigDecimal totalPrice;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private LocalDateTime updateTime;
}
