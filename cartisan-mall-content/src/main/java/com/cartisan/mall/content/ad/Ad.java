package com.cartisan.mall.content.ad;

import com.cartisan.domain.AbstractEntity;
import com.cartisan.domain.AggregateRoot;
import lombok.Getter;

import javax.persistence.*;
import java.util.Date;

/**
 * @author colin
 */
@Entity
@Table(name = "cnt_ads")
@Getter
public class Ad extends AbstractEntity implements AggregateRoot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "position")
    private String position;

    @Column(name = "start")
    private Date start;

    @Column(name = "end")
    private Date end;

    @Column(name = "status")
    private Integer status;

    @Column(name = "image")
    private String image;

    @Column(name = "url")
    private String url;

    @Column(name = "remarks")
    private String remarks;

    protected Ad() {
    }

    public Ad(String name, String position, Date start, Date end, Integer status, String image, String url, String remarks) {

        this.name = name;
        this.position = position;
        this.start = start;
        this.end = end;
        this.status = status;
        this.image = image;
        this.url = url;
        this.remarks = remarks;
    }

    public void describe(String name, String position, Date start, Date end, Integer status, String image, String url, String remarks) {
        this.name = name;
        this.position = position;
        this.start = start;
        this.end = end;
        this.status = status;
        this.image = image;
        this.url = url;
        this.remarks = remarks;
    }
}
