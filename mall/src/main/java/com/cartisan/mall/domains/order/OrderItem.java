package com.cartisan.mall.domains.order;

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
@Table(name = "mall_order_item")
@Data
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
}