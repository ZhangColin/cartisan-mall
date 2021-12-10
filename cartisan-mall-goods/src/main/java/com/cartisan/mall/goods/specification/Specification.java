package com.cartisan.mall.goods.specification;

import com.cartisan.domain.AbstractEntity;
import com.cartisan.domain.AggregateRoot;
import com.cartisan.repository.StringListConverter;
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

    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "name")
    private String name;

    @Column(name = "value_options")
    @Convert(converter = StringListConverter.class)
    private List<String> valueOptions;

    @Column(name = "manual_add")
    private Boolean manualAdd;

    @Column(name = "enable")
    private Boolean enable;

    @Column(name = "sequence")
    private Integer sequence;

    protected Specification() {}

    public Specification(Long categoryId, String name, List<String> valueOptions, Boolean manualAdd, Boolean enable, Integer sequence) {
        this.categoryId = categoryId;
        this.name = name;
        this.valueOptions = valueOptions;
        this.manualAdd = manualAdd;
        this.enable = enable;
        this.sequence = sequence;
    }

    public void describe(Long categoryId, String name, List<String> valueOptions, Boolean manualAdd, Boolean enable, Integer sequence) {
        this.categoryId = categoryId;
        this.name = name;
        this.valueOptions = valueOptions;
        this.manualAdd = manualAdd;
        this.enable = enable;
        this.sequence = sequence;
    }
}
