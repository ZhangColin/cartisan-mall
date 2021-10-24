package com.cartisan.mall.goods.template.domain;

import com.cartisan.domain.AbstractEntity;
import com.cartisan.domain.AggregateRoot;
import com.cartisan.mall.goods.template.repository.StringListConverter;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;

/**
 * @author colin
 */
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
    @Convert(converter = StringListConverter.class)
    private List<String> options;

    @Column(name = "sequence")
    private Integer sequence;

    protected Specification() {}

    public Specification(Long templateId, String name, List<String> options, Integer sequence) {

        this.templateId = templateId;
        this.name = name;
        this.options = options;
        this.sequence = sequence;
    }

    public void describe(Long templateId, String name, List<String> options, Integer sequence) {
        this.templateId = templateId;
        this.name = name;
        this.options = options;
        this.sequence = sequence;
    }
}
