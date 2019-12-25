package com.lg.shop.dao;

import com.lg.shop.entity.Cart;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author L
 * @version 1.0
 * @ClassName: CartDao
 * @date: 2019/12/24 10:26
 * @since JDK 1.8
 */
@Repository
public interface CartDao {

    @Insert("insert into cart(pid, pimage, pname, shop_price, num ,cart_price, uname) values(#{pid}, " +
            "#{pimage}, #{pname}, #{shopPrice}, #{num}, #{cartPrice}, #{uname})")
    Integer addCart(Cart cart);

    @Select("select * from cart where uname=#{username}")
    List<Cart> getCart(String username);

    @Select("select * from cart where pid=#{pid}")
    Cart findByPid(String pid);

    @Update("update cart set num=#{num},cart_price=#{cartPrice} where id=#{id}")
    Integer updateById(Cart cart);

    @Delete("delete from cart where id=#{id}")
    Integer deleteById(Integer id);

    @Delete("delete from cart where uname=#{username}")
    Integer deleteCart(String username);
}
