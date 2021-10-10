package com.cartisan.mall.domains.shipping;

import com.cartisan.domain.AbstractEntity;
import com.cartisan.domain.AggregateRoot;
import lombok.Data;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/**
 * @author colin
 */
@Entity
@Table(name = "mall_shipping")
@Data
@ToString
public class Shipping extends AbstractEntity implements AggregateRoot {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户 Id
     */
    @Column(name = "user_id")
    @Setter
    private Long userId;

    /**
     * 收货姓名
     */
    @Column(name = "receiver_name")
    private String receiverName;

    /**
     * 收货固定电话
     */
    @Column(name = "receiver_phone")
    private String receiverPhone;

    /**
     * 收货移动电话
     */
    @Column(name = "receiver_mobile")
    private String receiverMobile;

    /**
     * 省份
     */
    @Column(name = "receiver_province")
    private String receiverProvince;

    /**
     * 城市
     */
    @Column(name = "receiver_city")
    private String receiverCity;

    /**
     * 区/县
     */
    @Column(name = "receiver_district")
    private String receiverDistrict;

    /**
     * 详细地址
     */
    @Column(name = "receiver_address")
    private String receiverAddress;

    /**
     * 邮编
     */
    @Column(name = "receiver_zip")
    private String receiverZip;
}
