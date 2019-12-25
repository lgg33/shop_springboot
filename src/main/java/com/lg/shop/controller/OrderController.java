package com.lg.shop.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lg.shop.common.response.CommonCode;
import com.lg.shop.common.response.QueryResponseResult;
import com.lg.shop.common.response.QueryResult;
import com.lg.shop.common.response.ResponseResult;
import com.lg.shop.dao.UserDao;
import com.lg.shop.entity.Cart;
import com.lg.shop.entity.Order;
import com.lg.shop.service.serviceimpl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author L
 * @version 1.0
 * @ClassName: OrderController
 * @date: 2019/12/24 19:17
 * @since JDK 1.8
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderServiceImpl orderService;

    @Autowired
    private UserDao userDao;

    @PostMapping("/addOrder")
    public Order addOrderItem(@RequestBody List<Cart> carts) {
        return orderService.addOrder(carts);
    }

    @PutMapping("/orderSubmit")
    public ResponseResult orderSubmit(@RequestBody Order order) {
        orderService.updateOrderById(order);
        return new ResponseResult(CommonCode.SUCCESS);
    }

    @GetMapping("/findList/{username}/{num}/{size}")
    public QueryResponseResult findList(@PathVariable("username") String username, @PathVariable("num") Integer num,
                                        @PathVariable("size") Integer size) {
        PageHelper.startPage(num, size);
        List<Order> orders = orderService.findList(userDao.findByUsername(username).getUid());
        PageInfo<Order> orderPageInfo = new PageInfo<>(orders);
        QueryResult<Order> result = new QueryResult<>();
        result.setList(orderPageInfo.getList());
        result.setTotal(orderPageInfo.getTotal());
        return new QueryResponseResult(CommonCode.SUCCESS, result);
    }
}
