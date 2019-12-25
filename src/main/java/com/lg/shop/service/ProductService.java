package com.lg.shop.service;

import com.lg.shop.common.response.QueryResponseResult;
import com.lg.shop.entity.Cart;
import com.lg.shop.entity.Product;

import java.util.List;

/**
 * @author L
 * @version 1.0
 * @ClassName: ProductService
 * @date: 2019/12/23 8:52
 * @since JDK 1.8
 */
public interface ProductService {
    List<Product> findHotProduct();

    List<Product> findNewProduct();

    QueryResponseResult findList(int page, int size, Product product);

    Integer addCart(Cart cart);

    List<Cart> getCart(String username);

//    Cart findByPid(String pid);
//
//    Integer updateById(Cart cart);
//
    Integer deleteById(Integer id);

    Integer deleteCart(String username);

    List<Product> findByPname(String pname);
}
