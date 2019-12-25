package com.lg.shop.dao;

import com.lg.shop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author L
 * @version 1.0
 * @ClassName: ProductRepository
 * @date: 2019/12/23 15:03
 * @since JDK 1.8
 */
public interface ProductRepository extends JpaRepository<Product, String> {
//    List<Product> findByCid(String cid);
//
//    List<Product> findByPnameLike(String pname);
}
