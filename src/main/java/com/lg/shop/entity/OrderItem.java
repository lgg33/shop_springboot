package com.lg.shop.entity;

import lombok.Data;

/**
 * @author L
 * @version 1.0
 * @ClassName: OrderItem
 * @date: 2019/12/24 19:24
 * @since JDK 1.8
 */
@Data
public class OrderItem {
    private String itemid;
    private int count;//购买的数量
    private double subtotal;//小计
    private Product product;//该项中购买的商品对象
    private Order order;
    private String oid;
    private String pid;
}
