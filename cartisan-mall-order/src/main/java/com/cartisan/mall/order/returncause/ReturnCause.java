package com.cartisan.mall.order.returncause;

import com.cartisan.domain.AbstractEntity;
import com.cartisan.domain.AggregateRoot;
import lombok.Getter;

import javax.persistence.*;

/**
 * @author colin
 */
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
