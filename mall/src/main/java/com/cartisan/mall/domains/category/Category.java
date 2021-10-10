package com.cartisan.mall.domains.category;

import com.cartisan.domain.AbstractEntity;
import com.cartisan.domain.AggregateRoot;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;

/**
 * @author colin
 */
@Entity
@Table(name = "mall_category")
@Getter
@ToString
public class Category extends AbstractEntity implements AggregateRoot {
    /**
     * 类别 Id
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 父类别 Id，当 Id=0 时说明是根节点，一级类别
     */
    @Column(name = "parent_id")
    private Long parentId;

    /**
     * 类别名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 类别状态
     * 1 - 正常
     * 2 - 已废弃
     */
    @Column(name = "status")
    private Integer status;

    /**
     * 排序编号，同类展示顺序，数值相待则自然排序
     */
    @Column(name = "sort_order")
    private Integer sortOrder;
}
