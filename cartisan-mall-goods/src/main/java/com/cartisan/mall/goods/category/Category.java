package com.cartisan.mall.goods.category;

import com.cartisan.domain.AbstractEntity;
import com.cartisan.domain.AggregateRoot;
import lombok.Getter;

import javax.persistence.*;

/**
 * @author colin
 */
@Entity
@Table(name = "gds_categories")
@Getter
public class Category extends AbstractEntity implements AggregateRoot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "parent_id")
    private Long parentId;

    @Column(name = "name")
    private String name;

    @Column(name = "sequence")
    private Integer sequence;

    protected Category() {}

    public Category(Long parentId, String name) {
        this.parentId = parentId;
        this.name = name;

        this.sequence = 0;
    }

    public void describe(String name) {
        this.name = name;
    }

    public void move(Long parentId, Integer sequence) {
        this.parentId = parentId;
        this.sequence = sequence;
    }
}
