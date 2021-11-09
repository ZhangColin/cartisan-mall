package com.cartisan.mall.order.returncause;

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
import java.lang.Boolean;
import java.lang.String;
import java.lang.Integer;

import static java.util.stream.Collectors.toList;

@Entity
@Table(name = "ord_return_causes")
@Getter
public class ReturnCause extends AbstractEntity implements AggregateRoot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "cause")
    private String cause;

    @Column(name = "enable")
    private Boolean enable;

    @Column(name = "sequence")
    private Integer sequence;

    protected ReturnCause() {}

    public ReturnCause(String cause, Boolean enable, Integer sequence) {

        this.cause = cause;
        this.enable = enable;
        this.sequence = sequence;
    }

    public void describe(String cause, Boolean enable, Integer sequence) {
        this.cause = cause;
        this.enable = enable;
        this.sequence = sequence;
    }
}
