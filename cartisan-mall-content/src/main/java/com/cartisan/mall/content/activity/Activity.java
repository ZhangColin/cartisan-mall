package com.cartisan.mall.content.activity;

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
@Table(name = "cnt_activities")
@Getter
public class Activity extends AbstractEntity implements AggregateRoot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "start")
    private Date start;

    @Column(name = "end")
    private Date end;

    @Column(name = "content")
    private String content;

    @Column(name = "status")
    private Integer status;

    protected Activity() {}

    public Activity(String title, Date start, Date end, String content, Integer status) {

        this.title = title;
        this.start = start;
        this.end = end;
        this.content = content;
        this.status = status;
    }

    public void describe(String title, Date start, Date end, String content, Integer status) {
        this.title = title;
        this.start = start;
        this.end = end;
        this.content = content;
        this.status = status;
    }
}
