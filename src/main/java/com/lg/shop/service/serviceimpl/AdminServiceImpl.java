package com.lg.shop.service.serviceimpl;

import com.lg.shop.dao.AdminDao;
import com.lg.shop.dao.ProductRepository;
import com.lg.shop.entity.Admin;
import com.lg.shop.entity.Category;
import com.lg.shop.entity.Order;
import com.lg.shop.entity.Product;
import com.lg.shop.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @author L
 * @version 1.0
 * @ClassName: AdminServiceImpl
 * @date: 2019/12/26 9:04
 * @since JDK 1.8
 */
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminDao adminDao;

    @Autowired
    ProductRepository productRepository;

    @Override
    public Admin find(Admin admin) {
        return adminDao.find(admin);
    }

    @Override
    public Integer addCategory(Category category) {
        category.setCid(UUID.randomUUID().toString().replace("-",""));
        return adminDao.addCategory(category);
    }

    @Override
    public List<Category> findCategoryById(String id) {
        return adminDao.findCategoryById(id);
    }

    @Override
    public Integer updateCategory(Category category) {
        return adminDao.upadteCategory(category);
    }

    @Override
    public Integer deleteCategory(String id) {
        return adminDao.deleteCategory(id);
    }

    @Override
    public List<Product> findProductList() {
        return adminDao.findProductList();
    }

    @Override
    public Integer addProduct(Product product) {
        product.setPid(UUID.randomUUID().toString().replace("-",""));
        productRepository.save(product);
        return 1;
    }

    @Override
    public List<Order> findOrderList() {
        return adminDao.findOrderList();
    }

    @Override
    public Integer deleteProduct(String id) {
        return adminDao.deleteProduct(id);
    }
}
