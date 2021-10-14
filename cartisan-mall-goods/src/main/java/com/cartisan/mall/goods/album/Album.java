package com.cartisan.mall.goods.album;

import com.cartisan.domain.AbstractEntity;
import com.cartisan.domain.AggregateRoot;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "gds_albums")
@Getter
public class Album extends AbstractEntity implements AggregateRoot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "image")
    private String image;

    @Column(name = "image_items")
    private String imageItems;

    protected Album() {}

    public Album(String title, String image, String imageItems) {

        this.title = title;
        this.image = image;
        this.imageItems = imageItems;
    }

    public void describe(String title, String image, String imageItems) {
        this.title = title;
        this.image = image;
        this.imageItems = imageItems;
    }
}
