package com.cartisan.mall.goods.template;

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

import static java.util.stream.Collectors.toList;

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
