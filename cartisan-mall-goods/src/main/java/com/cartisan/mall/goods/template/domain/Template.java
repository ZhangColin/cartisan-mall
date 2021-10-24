package com.cartisan.mall.goods.template.domain;

import com.cartisan.domain.AbstractEntity;
import com.cartisan.domain.AggregateRoot;
import lombok.Getter;

import javax.persistence.*;

/**
 * @author colin
 */
@Entity
@Table(name = "gds_templates")
@Getter
public class Template extends AbstractEntity implements AggregateRoot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    protected Template() {}

    public Template(String name) {

        this.name = name;
    }

    public void describe(String name) {
        this.name = name;
    }
}
