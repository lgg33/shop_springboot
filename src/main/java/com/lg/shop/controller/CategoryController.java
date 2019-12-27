package com.lg.shop.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lg.shop.common.response.CommonCode;
import com.lg.shop.common.response.QueryResponseResult;
import com.lg.shop.common.response.QueryResult;
import com.lg.shop.entity.Category;
import com.lg.shop.service.serviceimpl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author L
 * @version 1.0
 * @ClassName: CategoryController
 * @date: 2019/12/23 8:57
 * @since JDK 1.8
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryServiceImpl categoryService;

    @GetMapping("/findAll")
    public QueryResponseResult findAll() {
        List<Category> categories = categoryService.findAll();
        QueryResult<Category> categoryQueryResult = new QueryResult<>();
        categoryQueryResult.setList(categories);
        return new QueryResponseResult(CommonCode.SUCCESS, categoryQueryResult);
    }

    @GetMapping("/findAllByPage/{num}/{size}")
    public QueryResponseResult findAllByPage(@PathVariable("num") Integer num, @PathVariable("size") Integer size) {
        PageHelper.startPage(num, size);
        List<Category> categories = categoryService.findAll();
        PageInfo<Category> pageInfo = new PageInfo<>(categories);
        QueryResult<Category> categoryQueryResult = new QueryResult<>();
        categoryQueryResult.setList(pageInfo.getList());
        categoryQueryResult.setTotal(pageInfo.getTotal());
        return new QueryResponseResult(CommonCode.SUCCESS, categoryQueryResult);
    }

    @GetMapping("/findById/{id}")
    public Category findById(@PathVariable("id") String id) {
        return categoryService.findById(id);
    }


}
