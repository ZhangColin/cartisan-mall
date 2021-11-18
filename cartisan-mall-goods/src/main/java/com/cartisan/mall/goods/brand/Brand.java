package com.cartisan.mall.goods.brand;

import com.cartisan.domain.AbstractEntity;
import com.cartisan.domain.AggregateRoot;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author colin
 */
@Entity
@Table(name = "gds_brands")
@Getter
public class Brand extends AbstractEntity implements AggregateRoot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "logo")
    private String logo;

    @Column(name = "description")
    private String description;

    @Column(name = "first_letter")
    private String firstLetter;

    @Column(name = "sequence")
    private Integer sequence;

    @ElementCollection
    @CollectionTable(name = "gds_brand_categories", joinColumns = @JoinColumn(name = "brand_id", referencedColumnName = "id"))
    @Column(name = "category_id")
    private List<Long> categoryIds = new ArrayList<>();

    protected Brand() {
    }

    public Brand(String name, String logo, String description, String firstLetter, Integer sequence) {
        this.name = name;
        this.logo = logo;
        this.description = description;
        this.firstLetter = firstLetter;
        this.sequence = sequence;
    }

    public void describe(String name, String logo, String description, String letter, Integer sequence) {
        this.name = name;
        this.logo = logo;
        this.description = description;
        this.firstLetter = letter;
        this.sequence = sequence;
    }

    public void addCategory(Long categoryId) {
        if (!this.categoryIds.contains(categoryId)) {
            this.categoryIds.add(categoryId);
        }
    }

    public void removeCategory(Long categoryId) {
        this.categoryIds.remove(categoryId);
    }
}
