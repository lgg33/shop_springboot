package com.lg.shop.service;

import com.lg.shop.entity.Admin;
import com.lg.shop.entity.Category;
import com.lg.shop.entity.Order;
import com.lg.shop.entity.Product;

import java.util.List;

/**
 * @author L
 * @version 1.0
 * @ClassName: AdminService
 * @date: 2019/12/26 9:03
 * @since JDK 1.8
 */
public interface AdminService {
    Admin find(Admin admin);

    Integer addCategory(Category category);

    List<Category> findCategoryById(String id);

    Integer updateCategory(Category category);

    Integer deleteCategory(String id);

    List<Product> findProductList();

    Integer addProduct(Product product);

    List<Order> findOrderList();

    Integer deleteProduct(String id);
}
