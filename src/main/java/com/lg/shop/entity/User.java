package com.lg.shop.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author L
 * @version 1.0
 * @ClassName: User
 * @date: 2019/12/22 15:56
 * @since JDK 1.8
 */
@Data
@Entity
@Table(name = "user")
public class User {
    @Id
    private String uid;
    private String username;
    private String password;
    private String name;
    private String email;
    private String telephone;
    private String birthday;
    private String sex;
    private int state;//0--未激活 1--激活
    private String code;//激活码
}
