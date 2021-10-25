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

    @Column(name = "image")
    private String image;

    @Column(name = "letter")
    private String letter;

    @Column(name = "sequence")
    private Integer sequence;

    @ElementCollection
    @CollectionTable(name = "gds_brand_categories", joinColumns = @JoinColumn(name = "brand_id", referencedColumnName = "id"))
    @Column(name = "category_id")
    private List<Long> categoryIds = new ArrayList<>();

    protected Brand() {
    }

    public Brand(String name, String image, String letter, Integer sequence) {

        this.name = name;
        this.image = image;
        this.letter = letter;
        this.sequence = sequence;
    }

    public void describe(String name, String image, String letter, Integer sequence) {
        this.name = name;
        this.image = image;
        this.letter = letter;
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
