package com.lg.shop.service;

import com.lg.shop.entity.Cart;
import com.lg.shop.entity.Order;

import java.util.List;

/**
 * @author L
 * @version 1.0
 * @ClassName: OrderService
 * @date: 2019/12/24 19:16
 * @since JDK 1.8
 */
public interface OrderService {
    Order addOrder(List<Cart> carts);

    Integer updateOrderById(Order order);

    List<Order> findList(String uid);
}
