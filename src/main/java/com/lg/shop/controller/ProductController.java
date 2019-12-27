package com.lg.shop.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lg.shop.common.response.CommonCode;
import com.lg.shop.common.response.QueryResponseResult;
import com.lg.shop.common.response.QueryResult;
import com.lg.shop.common.response.ResponseResult;
import com.lg.shop.component.RedisUtil;
import com.lg.shop.entity.Cart;
import com.lg.shop.entity.Product;
import com.lg.shop.service.serviceimpl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author L
 * @version 1.0
 * @ClassName: ProductController
 * @date: 2019/12/23 8:44
 * @since JDK 1.8
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    ProductServiceImpl productService;

    //热门商品
    @GetMapping("/hotProduct")
    public QueryResponseResult hotProduct() {
        List<Product> hotProduct = productService.findHotProduct();
        QueryResult<Product> queryResult = new QueryResult<>();
        queryResult.setList(hotProduct);
        return new QueryResponseResult(CommonCode.SUCCESS, queryResult);
    }

    //最新商品
    @GetMapping("/newProduct")
    public QueryResponseResult newProduct() {
        List<Product> newProduct = productService.findNewProduct();
        QueryResult<Product> queryResult = new QueryResult<>();
        queryResult.setList(newProduct);
        return new QueryResponseResult(CommonCode.SUCCESS, queryResult);
    }

    //分页查询商品
    @GetMapping("/findList/{page}/{size}")
    public QueryResponseResult findList(@PathVariable("page") int page, @PathVariable("size") int size,
                                        Product product) {
        return productService.findList(page, size, product);
    }

    /*@PostMapping("/addCart")
    public ResponseResult addCart(@RequestBody Cart cart) {
        Cart c = (Cart) redisUtil.lGet("cart", 0, -1);
        if (c != null) {
            c.setNum(c.getNum() + 1);
            c.setCartPrice(c.getShop_price() * c.getNum());
            redisUtil.lSet("cart", c);
            return new ResponseResult(CommonCode.SUCCESS);
        }
        redisUtil.lSet("cart", cart);
        return new ResponseResult(CommonCode.SUCCESS);
    }*/
    //添加购物车
    @PostMapping("/addCart")
    public ResponseResult addCart(@RequestBody Cart cart) {
        productService.addCart(cart);
        return new ResponseResult(CommonCode.SUCCESS);
    }

    //获取购物车的数据
    @GetMapping("/getCart/{username}")
    public QueryResponseResult getCart(@PathVariable("username") String username) {
        List<Cart> carts = productService.getCart(username);
        QueryResult<Cart> queryResult = new QueryResult<>();
        queryResult.setList(carts);
        return new QueryResponseResult(CommonCode.SUCCESS, queryResult);
    }

    @DeleteMapping("/deleteCartById/{id}")
    public ResponseResult delete(@PathVariable("id") Integer id) {
        productService.deleteById(id);
        return new ResponseResult(CommonCode.SUCCESS);
    }

    @DeleteMapping("/deleteCart/{username}")
    public ResponseResult deleteCart(@PathVariable("username") String username) {
        productService.deleteCart(username);
        return new ResponseResult(CommonCode.SUCCESS);
    }

    @GetMapping("/findByName/{name}/{num}/{size}")
    public QueryResponseResult findByName(@PathVariable("name") String name, @PathVariable("num") Integer num,
                                          @PathVariable("size") Integer size) {
        PageHelper.startPage(num, size);
        List<Product> products = productService.findByPname(name);
//        System.out.println(products);
        PageInfo<Product> productPageInfo = new PageInfo<>(products);
        QueryResult<Product> result = new QueryResult<>();
        result.setList(productPageInfo.getList());
        result.setTotal(productPageInfo.getTotal());
        return new QueryResponseResult(CommonCode.SUCCESS, result);
    }
}
