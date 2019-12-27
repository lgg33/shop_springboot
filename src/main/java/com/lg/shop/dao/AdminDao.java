package com.lg.shop.dao;

import com.lg.shop.entity.Admin;
import com.lg.shop.entity.Category;
import com.lg.shop.entity.Order;
import com.lg.shop.entity.Product;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author L
 * @version 1.0
 * @ClassName: Admin
 * @date: 2019/12/26 9:00
 * @since JDK 1.8
 */
@Repository
public interface AdminDao {
    @Select("select * from admin where name=#{name} and password=#{password}")
    Admin find(Admin admin);

    @Insert("insert into category (cid, cname) values(#{cid}, #{cname})")
    Integer addCategory(Category category);

    @Select("select * from category where cid = #{id}")
    List<Category> findCategoryById(String id);

    @Update("update category set cname = #{cname} where cid = #{cid}")
    Integer upadteCategory(Category category);

    @Delete("delete from category where cid = #{id}")
    Integer deleteCategory(String id);

    @Select("select * from product")
    List<Product> findProductList();

    @Select("select * from orders")
    List<Order> findOrderList();

    @Delete("delete from product where pid=#{oid}")
    Integer deleteProduct(String oid);

}
