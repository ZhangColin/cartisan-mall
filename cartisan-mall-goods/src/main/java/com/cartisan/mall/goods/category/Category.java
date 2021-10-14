package com.cartisan.mall.goods.category;

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
@Table(name = "gds_categories")
@Getter
public class Category extends AbstractEntity implements AggregateRoot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "parent_id")
    private Long parentId;

    @Column(name = "template_id")
    private Long templateId;

    @Column(name = "name")
    private String name;

    @Column(name = "is_show")
    private String isShow;

    @Column(name = "is_menu")
    private String isMenu;

    @Column(name = "sequence")
    private Integer sequence;

    protected Category() {}

    public Category(Long parentId, Long templateId, String name, String isShow, String isMenu, Integer sequence) {
        
        this.parentId = parentId;
        this.templateId = templateId;
        this.name = name;
        this.isShow = isShow;
        this.isMenu = isMenu;
        this.sequence = sequence;
    }

    public void describe(Long parentId, Long templateId, String name, String isShow, String isMenu, Integer sequence) {
        this.parentId = parentId;
        this.templateId = templateId;
        this.name = name;
        this.isShow = isShow;
        this.isMenu = isMenu;
        this.sequence = sequence;
    }
}
