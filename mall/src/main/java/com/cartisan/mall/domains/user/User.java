package com.cartisan.mall.domains.user;

import com.cartisan.domains.AbstractEntity;
import com.cartisan.domains.AggregateRoot;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

/**
 * @author colin
 */
@Entity
@Table(name = "mall_user")
@Data
@ToString
public class User extends AbstractEntity implements AggregateRoot {
    /**
     * 用户 Id
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户名
     */
    @Column(name = "username")
    private String userName;

    /**
     * 密码
     * MD5 加密
     */
    @Column(name = "password")
    private String password;

    /**
     * email
     */
    @Column(name = "email")
    private String email;

    /**
     * phone
     */
    @Column(name = "phone")
    private String phone;

    /**
     * 找回密码问题
     */
    @Column(name = "question")
    private String question;

    /**
     * 找回密码答案
     */
    @Column(name = "answer")
    private String answer;

    /**
     * 角色
     */
    @Column(name = "role")
    private Integer role;

    public User(String userName, String password, String email, Integer role) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    protected User() {

    }
}
