package com.lg.shop.dao;

import com.lg.shop.entity.Order;
import com.lg.shop.entity.OrderItem;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author L
 * @version 1.0
 * @ClassName: OrderDao
 * @date: 2019/12/24 19:15
 * @since JDK 1.8
 */
@Repository
public interface OrderDao {
    @Insert("insert into `orders` (oid, ordertime, total, uid, state) " +
            "values (#{oid},#{orderTime},#{total},#{uid},#{state})")
    Integer addOrder(Order order);

    @Insert("insert into orderitem (itemid,count,subtotal,pid,oid) values (#{itemid},#{count},#{subtotal},#{pid},#{oid})")
    Integer addOrderItem(OrderItem orderItem);

    @Update("update orders set total=#{total} where oid=#{oid}")
    Integer updateTotal(Order order);

    @Select("select * from orders where uid=#{uid}")
    List<Order> findList(String uid);

    @Select("select * from orderitem where oid=#{oid}")
    List<OrderItem> findOrderItem(String oid);

    @Select("select * from orders where oid=#{id}")
    Order findById(String id);

    @Update("update orders set address=#{address},name=#{name},telephone=#{telephone},state=1 where oid=#{oid}")
    Integer updateOrderById(Order order);
}
