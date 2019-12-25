package com.lg.shop.dao;

import com.lg.shop.entity.Category;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author L
 * @version 1.0
 * @ClassName: CategoryDao
 * @date: 2019/12/23 8:57
 * @since JDK 1.8
 */
@Repository
public interface CategoryDao {
    @Select("select * from category")
    List<Category> findAll();

    @Select("select * from category where cid = #{id}")
    Category findById(String id);
}
