package com.cartisan.mall.content.activity;

import com.cartisan.domain.AbstractEntity;
import com.cartisan.domain.AggregateRoot;
import lombok.Getter;

import javax.persistence.*;
import java.util.Date;

/**
 * @author colin
 */
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

    protected Activity() {
    }

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
