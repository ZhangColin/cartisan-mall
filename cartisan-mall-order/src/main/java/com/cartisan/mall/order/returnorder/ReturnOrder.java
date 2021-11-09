package com.cartisan.mall.order.returnorder;

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
import java.lang.Boolean;
import java.lang.String;
import java.lang.Integer;

import static java.util.stream.Collectors.toList;

@Entity
@Table(name = "ord_return_orders")
@Getter
public class ReturnOrder extends AbstractEntity implements AggregateRoot {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "applied")
    private Date applied;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "username")
    private String username;

    @Column(name = "contract")
    private String contract;

    @Column(name = "contract_mobile")
    private String contractMobile;

    @Column(name = "type")
    private Integer type;

    @Column(name = "refund_amount")
    private Integer refundAmount;

    @Column(name = "refund_freight")
    private Boolean refundFreight;

    @Column(name = "status")
    private Integer status;

    @Column(name = "processed")
    private Date processed;

    @Column(name = "return_cause")
    private Long returnCause;

    @Column(name = "evidence")
    private String evidence;

    @Column(name = "description")
    private String description;

    @Column(name = "remark")
    private String remark;

    protected ReturnOrder() {}

    public ReturnOrder(Long id, Long orderId, Date applied, Long userId, String username, String contract, String contractMobile, Integer type, Integer refundAmount, Boolean refundFreight, Integer status, Date processed, Long returnCause, String evidence, String description, String remark) {
        this.id = id;
        this.orderId = orderId;
        this.applied = applied;
        this.userId = userId;
        this.username = username;
        this.contract = contract;
        this.contractMobile = contractMobile;
        this.type = type;
        this.refundAmount = refundAmount;
        this.refundFreight = refundFreight;
        this.status = status;
        this.processed = processed;
        this.returnCause = returnCause;
        this.evidence = evidence;
        this.description = description;
        this.remark = remark;
    }

    public void describe(Long orderId, Date applied, Long userId, String username, String contract, String contractMobile, Integer type, Integer refundAmount, Boolean refundFreight, Integer status, Date processed, Long returnCause, String evidence, String description, String remark) {
        this.orderId = orderId;
        this.applied = applied;
        this.userId = userId;
        this.username = username;
        this.contract = contract;
        this.contractMobile = contractMobile;
        this.type = type;
        this.refundAmount = refundAmount;
        this.refundFreight = refundFreight;
        this.status = status;
        this.processed = processed;
        this.returnCause = returnCause;
        this.evidence = evidence;
        this.description = description;
        this.remark = remark;
    }
}
