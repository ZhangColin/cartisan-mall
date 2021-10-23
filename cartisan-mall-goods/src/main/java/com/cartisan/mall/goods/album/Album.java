package com.cartisan.mall.goods.album;

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
@Table(name = "gds_albums")
@Getter
public class Album extends AbstractEntity implements AggregateRoot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "cover_image")
    private String coverImage;

    @Column(name = "image_items")
    @Convert(converter = ImageItemsConverter.class)
    private List<String> imageItems = new ArrayList<>();

    protected Album() {
    }

    public Album(String title, String coverImage) {
        this.title = title;
        this.coverImage = coverImage;
    }

    public void describe(String title, String coverImage) {
        this.title = title;
        this.coverImage = coverImage;
    }

    public void addImage(String imageUrl) {
        if (!imageItems.contains(imageUrl)) {
            imageItems.add(imageUrl);
        }
    }

    public void removeImage(String imageUrl) {
        imageItems.remove(imageUrl);
    }
}
