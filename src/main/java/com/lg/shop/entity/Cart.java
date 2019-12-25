package com.lg.shop.entity;

import lombok.Data;

/**
 * @author L
 * @version 1.0
 * @ClassName: Cart
 * @date: 2019/12/23 20:46
 * @since JDK 1.8
 */
@Data
public class Cart {
    private  Integer id;
    private String pid;
    private String pimage;
    private String pname;
    private double shopPrice;
    private Integer num;
    private double cartPrice;
    private String uid;
    private String uname;
}
