package com.lg.shop.service;

import com.lg.shop.entity.User;

/**
 * @author L
 * @version 1.0
 * @ClassName: UserService
 * @date: 2019/12/22 14:02
 * @since JDK 1.8
 */
public interface UserService {
    void addUser(User user);

    User findByCode(String code);

    User findByUsername(String username);

    User login(String username, String password);
}
