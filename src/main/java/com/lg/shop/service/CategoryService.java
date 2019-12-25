package com.lg.shop.service;

import com.lg.shop.entity.Category;

import java.util.List;

/**
 * @author L
 * @version 1.0
 * @ClassName: CategoryService
 * @date: 2019/12/23 8:57
 * @since JDK 1.8
 */
public interface CategoryService {
    List<Category> findAll();

    Category findById(String id);
}
