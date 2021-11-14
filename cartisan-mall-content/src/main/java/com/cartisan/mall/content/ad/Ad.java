package com.cartisan.mall.content.ad;

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
import java.util.Date;
import java.lang.String;
import java.lang.Integer;

import static java.util.stream.Collectors.toList;

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

    protected Ad() {}

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
