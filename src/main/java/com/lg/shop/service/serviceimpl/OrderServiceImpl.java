package com.lg.shop.service.serviceimpl;

import com.lg.shop.dao.CartDao;
import com.lg.shop.dao.OrderDao;
import com.lg.shop.dao.ProductRepository;
import com.lg.shop.dao.UserDao;
import com.lg.shop.entity.Cart;
import com.lg.shop.entity.Order;
import com.lg.shop.entity.OrderItem;
import com.lg.shop.entity.Product;
import com.lg.shop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author L
 * @version 1.0
 * @ClassName: OrderServiceImpl
 * @date: 2019/12/24 19:16
 * @since JDK 1.8
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderDao orderDao;

    @Autowired
    UserDao userDao;

    @Autowired
    CartDao cartDao;

    @Autowired
    ProductRepository productRepository;

    @Override
    public Order addOrder(List<Cart> carts) {
        Order order = new Order();
        order.setOid(UUID.randomUUID().toString().replace("-", ""));
        order.setOrderTime(new Date());
        order.setState(0);
        order.setUid(userDao.findByUsername(carts.get(0).getUname()).getUid());
        orderDao.addOrder(order);
        double total = 0;
        for (Cart cart : carts) {
            total += cart.getCartPrice();
            OrderItem orderItem = new OrderItem();
            orderItem.setItemid(UUID.randomUUID().toString().replace("-", ""));
            orderItem.setCount(cart.getNum());
            orderItem.setSubtotal(cart.getCartPrice());
            orderItem.setOid(order.getOid());
            orderItem.setPid(cart.getPid());
            orderDao.addOrderItem(orderItem);
            cartDao.deleteById(cart.getId());
        }
        order.setTotal(total);
        orderDao.updateTotal(order);
        return orderDao.findById(order.getOid());
    }

    @Override
    public Integer updateOrderById(Order order) {
        return orderDao.updateOrderById(order);
    }

    @Override
    public List<Order> findList(String uid) {
        List<Order> orders = orderDao.findList(uid);
        for (Order order : orders) {
            List<OrderItem> orderItems = orderDao.findOrderItem(order.getOid());
            for (OrderItem orderItem : orderItems) {
                Optional<Product> optional = productRepository.findById(orderItem.getPid());
                if (optional.isPresent()) orderItem.setProduct(optional.get());
            }
            order.setOrderItems(orderItems);
        }
        return orders;
    }
}
