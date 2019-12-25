package com.lg.shop.service.serviceimpl;

import com.lg.shop.common.response.CommonCode;
import com.lg.shop.common.response.QueryResponseResult;
import com.lg.shop.common.response.QueryResult;
import com.lg.shop.dao.CartDao;
import com.lg.shop.dao.ProductDao;
import com.lg.shop.dao.ProductRepository;
import com.lg.shop.entity.Cart;
import com.lg.shop.entity.Product;
import com.lg.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author L
 * @version 1.0
 * @ClassName: ProductServiceImpl
 * @date: 2019/12/23 8:52
 * @since JDK 1.8
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductDao productDao;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CartDao cartDao;

    @Override
    public List<Product> findHotProduct() {
        return productDao.findHotProduct();
    }

    @Override
    public List<Product> findNewProduct() {
        return productDao.findNewProduct();
    }

    @Override
    public QueryResponseResult findList(int page, int size, Product product) {
        Example<Product> productExample = Example.of(product);
        if (page <= 0) {
            page = 1;
        }
        page--;
        if (size <= 0) {
            size = 10;
        }
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> all = productRepository.findAll(productExample, pageable);
        QueryResult<Product> queryResult = new QueryResult<>();
        queryResult.setList(all.getContent());
        queryResult.setTotal(all.getTotalElements());
        return new QueryResponseResult(CommonCode.SUCCESS,queryResult);
    }

    @Override
    public Integer addCart(Cart cart) {
        Cart cart1 = cartDao.findByPid(cart.getPid());
        if (cart1 != null) {
            Integer i = cart1.getNum();
            i++;
            cart1.setNum(i);
            cart1.setCartPrice(cart1.getShopPrice() * i);
            return cartDao.updateById(cart1);
        }
        return cartDao.addCart(cart);
    }

    @Override
    public List<Cart> getCart(String username) {
        return cartDao.getCart(username);
    }

    @Override
    public Integer deleteById(Integer id) {
        return cartDao.deleteById(id);
    }

    @Override
    public Integer deleteCart(String username) {
        return cartDao.deleteCart(username);
    }

    @Override
    public List<Product> findByPname(String pname) {
        return productDao.findByName(pname);
    }
}

