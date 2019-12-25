package com.lg.shop.service.serviceimpl;

import com.lg.shop.component.RedisUtil;
import com.lg.shop.dao.CategoryDao;
import com.lg.shop.entity.Category;
import com.lg.shop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author L
 * @version 1.0
 * @ClassName: CategoryServiceImpl
 * @date: 2019/12/23 8:58
 * @since JDK 1.8
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    CategoryDao categoryDao;

    @Override
    public List<Category> findAll() {
        List<Category> categories = categoryDao.findAll();
//        redisUtil.set("categories", list);
//        List<Category> categories = (List<Category>) redisUtil.get("categories");
        return categories;
    }

    @Override
    public Category findById(String id) {
        return categoryDao.findById(id);
    }
}
