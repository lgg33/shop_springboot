package com.lg.shop;

import com.lg.shop.dao.CategoryDao;
import com.lg.shop.entity.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author L
 * @version 1.0
 * @ClassName: TestJpa
 * @date: 2019/12/23 9:31
 * @since JDK 1.8
 */
@SpringBootTest
public class TestJpa {

    @Autowired
    CategoryDao categoryDao;

    @Test
    public void testJ() {
        List<Category> categorys = categoryDao.findAll();
        System.out.println(categorys);
    }
}
