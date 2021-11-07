package com.cartisan.mall.goods.goods.domain;

import com.cartisan.domain.AbstractEntity;
import com.cartisan.domain.AggregateRoot;
import lombok.Getter;

import javax.persistence.*;

/**
 * @author colin
 */
@Entity
@Table(name = "gds_spu_audits")
@Getter
public class SpuAudit extends AbstractEntity implements AggregateRoot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "spu_id")
    private Long spuId;

    @Column(name = "result")
    private String result;

    @Column(name = "feedback")
    private String feedback;

    @Column(name = "auditor")
    private String auditor;

    protected SpuAudit() {}

    public SpuAudit(Long spuId, String result, String feedback, String auditor) {
        this.spuId = spuId;
        this.result = result;
        this.feedback = feedback;
        this.auditor = auditor;
    }

}
