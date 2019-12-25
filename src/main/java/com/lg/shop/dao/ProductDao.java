package com.lg.shop.dao;

import com.lg.shop.entity.Product;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author L
 * @version 1.0
 * @ClassName: ProductDao
 * @date: 2019/12/23 8:52
 * @since JDK 1.8
 */
@Repository
public interface ProductDao {
    @Select("select * from product where is_hot = 1 limit 0,9")
    List<Product> findHotProduct();

    @Select("select * from product order by pdate desc limit 0,9")
    List<Product> findNewProduct();

    @Select("select * from product where pname like concat('%', #{name}, '%')")
    List<Product> findByName(String name);
}
