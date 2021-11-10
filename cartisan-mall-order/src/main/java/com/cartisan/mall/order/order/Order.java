package com.cartisan.mall.order.order;

import com.cartisan.domain.AbstractEntity;
import com.cartisan.domain.AggregateRoot;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author colin
 */
@Entity
@Table(name = "ord_orders")
@Getter
public class Order extends AbstractEntity implements AggregateRoot {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "total_amount")
    private Integer totalAmount;

    @Column(name = "discount_amount")
    private Integer discountAmount;

    @Column(name = "freight")
    private Integer freight;

    @Column(name = "actual_amount")
    private Integer actualAmount;

    @Column(name = "pay_type")
    private Integer payType;

    @Column(name = "placed")
    private Date placed;

    @Column(name = "paid")
    private Date paid;

    @Column(name = "delivered")
    private Date delivered;

    @Column(name = "completed")
    private Date completed;

    @Column(name = "closed")
    private Date closed;

    @Column(name = "shipping_name")
    private String shippingName;

    @Column(name = "shipping_code")
    private String shippingCode;

    @Column(name = "username")
    private String username;

    @Column(name = "buyer_message")
    private String buyerMessage;

    @Column(name = "buyer_rate")
    private Boolean buyerRate;

    @Column(name = "receiver_contact")
    private String receiverContact;

    @Column(name = "receiver_mobile")
    private String receiverMobile;

    @Column(name = "receiver_address")
    private String receiverAddress;

    @Column(name = "source_type")
    private Integer sourceType;

    @Column(name = "order_status")
    private Integer orderStatus;

    @Column(name = "pay_status")
    private Integer payStatus;

    protected Order() {}

    public Order(Long id, Integer totalAmount, Integer discountAmount, Integer freight, Integer actualAmount, Integer payType, Date placed, Date paid, Date delivered, Date completed, Date closed, String shippingName, String shippingCode, String username, String buyerMessage, Boolean buyerRate, String receiverContact, String receiverMobile, String receiverAddress, Integer sourceType, Integer orderStatus, Integer payStatus) {
        this.id = id;
        this.totalAmount = totalAmount;
        this.discountAmount = discountAmount;
        this.freight = freight;
        this.actualAmount = actualAmount;
        this.payType = payType;
        this.placed = placed;
        this.paid = paid;
        this.delivered = delivered;
        this.completed = completed;
        this.closed = closed;
        this.shippingName = shippingName;
        this.shippingCode = shippingCode;
        this.username = username;
        this.buyerMessage = buyerMessage;
        this.buyerRate = buyerRate;
        this.receiverContact = receiverContact;
        this.receiverMobile = receiverMobile;
        this.receiverAddress = receiverAddress;
        this.sourceType = sourceType;
        this.orderStatus = orderStatus;
        this.payStatus = payStatus;
    }

    public void describe(Integer totalAmount, Integer discountAmount, Integer freight, Integer actualAmount, Integer payType, Date placed, Date paid, Date delivered, Date completed, Date closed, String shippingName, String shippingCode, String username, String buyerMessage, Boolean buyerRate, String receiverContact, String receiverMobile, String receiverAddress, Integer sourceType, Integer orderStatus, Integer payStatus) {
        this.totalAmount = totalAmount;
        this.discountAmount = discountAmount;
        this.freight = freight;
        this.actualAmount = actualAmount;
        this.payType = payType;
        this.placed = placed;
        this.paid = paid;
        this.delivered = delivered;
        this.completed = completed;
        this.closed = closed;
        this.shippingName = shippingName;
        this.shippingCode = shippingCode;
        this.username = username;
        this.buyerMessage = buyerMessage;
        this.buyerRate = buyerRate;
        this.receiverContact = receiverContact;
        this.receiverMobile = receiverMobile;
        this.receiverAddress = receiverAddress;
        this.sourceType = sourceType;
        this.orderStatus = orderStatus;
        this.payStatus = payStatus;
    }
}
