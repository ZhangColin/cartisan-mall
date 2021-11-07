package com.cartisan.mall.goods.goods.domain;

import com.cartisan.domain.AbstractEntity;
import com.cartisan.domain.AggregateRoot;
import lombok.Getter;

import javax.persistence.*;

/**
 * @author colin
 */
@Entity
@Table(name = "gds_spu_logs")
@Getter
public class SpuLog extends AbstractEntity implements AggregateRoot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "spu_id")
    private Long spuId;

    @Column(name = "content")
    private String content;

    @Column(name = "operator")
    private String operator;

    protected SpuLog() {}

    public SpuLog(Long spuId, String content, String operator) {
        this.spuId = spuId;
        this.content = content;
        this.operator = operator;
    }
}
