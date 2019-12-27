package com.lg.shop.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lg.shop.common.response.CommonCode;
import com.lg.shop.common.response.QueryResponseResult;
import com.lg.shop.common.response.QueryResult;
import com.lg.shop.common.response.ResponseResult;
import com.lg.shop.entity.*;
import com.lg.shop.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * @author L
 * @version 1.0
 * @ClassName: AdminController
 * @date: 2019/12/26 9:06
 * @since JDK 1.8
 */
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    AdminService adminService;

    @PostMapping("/login")
    public ResponseResult find(@RequestBody Admin admin) {
        Admin admin1 = adminService.find(admin);
        if (admin1 == null) return new ResponseResult(CommonCode.LOGIN_FAIL);
        return new ResponseResult(CommonCode.SUCCESS);
    }

    @PostMapping("/addCategory")
    public ResponseResult addCategory(@RequestBody Category category) {
        adminService.addCategory(category);
        return new ResponseResult(CommonCode.SUCCESS);
    }

    @GetMapping("/categoryById/{id}")
    public QueryResponseResult categoryById(@PathVariable("id") String id) {
        List<Category> category = adminService.findCategoryById(id);
        QueryResult<Category> result = new QueryResult<>();
        result.setList(category);
        return new QueryResponseResult(CommonCode.SUCCESS, result);
    }

    @PutMapping("/updateCategory")
    public ResponseResult updateCategory(@RequestBody Category category) {
        adminService.updateCategory(category);
        return new ResponseResult(CommonCode.SUCCESS);
    }

    @DeleteMapping("/deleteCategory/{id}")
    public ResponseResult deleteCategory(@PathVariable("id") String id) {
        adminService.deleteCategory(id);
        return new ResponseResult(CommonCode.SUCCESS);
    }

    @GetMapping("/findProductList/{num}/{size}")
    public QueryResponseResult findProductList(@PathVariable("num") Integer num, @PathVariable("size") Integer size) {
        PageHelper.startPage(num, size);
        List<Product> productList = adminService.findProductList();
        PageInfo<Product> productPageInfo = new PageInfo<>(productList);
        QueryResult<Product> result = new QueryResult<>();
        result.setList(productPageInfo.getList());
        result.setTotal(productPageInfo.getTotal());
        return  new QueryResponseResult(CommonCode.SUCCESS, result);
    }

    @PostMapping("/saveFile")
    public Message saveFile(MultipartFile file) {
        String filename = file.getOriginalFilename();
        assert filename != null;
        String suffix = filename.substring(filename.lastIndexOf("."));
        String uuid = UUID.randomUUID().toString();
        String image = uuid+suffix;
        String dir = "D:/webstormspace/shop/shopcli3/public/products/";
        File dest = new File(dir + image);
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Message message = new Message();
        message.setUrl("products/" + image);
        return message;
    }

    @PostMapping("/addProduct")
    public ResponseResult addProduct(@RequestBody Product product) {
        adminService.addProduct(product);
        return new ResponseResult(CommonCode.SUCCESS);
    }

    @GetMapping("/findOrderList/{num}/{size}")
    public QueryResponseResult findOrderList(@PathVariable("num") Integer num, @PathVariable("size") Integer size) {
        PageHelper.startPage(num, size);
        List<Order> orders = adminService.findOrderList();
        PageInfo<Order> orderPageInfo = new PageInfo<>(orders);
        QueryResult<Order> result = new QueryResult<>();
        result.setList(orderPageInfo.getList());
        result.setTotal(orderPageInfo.getTotal());
        return  new QueryResponseResult(CommonCode.SUCCESS, result);
    }

    @DeleteMapping("/deleteProduct/{id}")
    public ResponseResult deleteProduct(@PathVariable("id") String id) {
        adminService.deleteProduct(id);
        return new ResponseResult(CommonCode.SUCCESS);
    }
}
