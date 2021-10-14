package com.cartisan.mall.goods.specification;

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
@Table(name = "gds_specifications")
@Getter
public class Specification extends AbstractEntity implements AggregateRoot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "template_id")
    private Long templateId;

    @Column(name = "name")
    private String name;

    @Column(name = "options")
    private String options;

    @Column(name = "sequence")
    private Integer sequence;

    protected Specification() {}

    public Specification(Long templateId, String name, String options, Integer sequence) {
        
        this.templateId = templateId;
        this.name = name;
        this.options = options;
        this.sequence = sequence;
    }

    public void describe(Long templateId, String name, String options, Integer sequence) {
        this.templateId = templateId;
        this.name = name;
        this.options = options;
        this.sequence = sequence;
    }
}
