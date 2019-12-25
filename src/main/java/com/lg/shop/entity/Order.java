package com.lg.shop.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;


/**
 * @author L
 * @version 1.0
 * @ClassName: Order
 * @date: 2019/12/24 19:11
 * @since JDK 1.8
 */
@Data
public class Order {
    private String oid;
    private Date orderTime;
    private double total;
    private int state;
    private String address;
    private String name;
    private String telephone;
    private String uid;
    private List<OrderItem> orderItems;
}
