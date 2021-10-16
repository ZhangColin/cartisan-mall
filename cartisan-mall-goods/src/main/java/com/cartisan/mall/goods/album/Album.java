package com.cartisan.mall.goods.album;

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
