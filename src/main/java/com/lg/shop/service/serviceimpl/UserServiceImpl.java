package com.lg.shop.service.serviceimpl;

import com.lg.shop.dao.UserDao;
import com.lg.shop.entity.User;
import com.lg.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author L
 * @version 1.0
 * @ClassName: UserServiceImpl
 * @date: 2019/12/22 14:03
 * @since JDK 1.8
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public User login(String username, String password) {
        return userDao.findByUsernameAndPassword(username, password);
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public User findByCode(String code) {
        return userDao.findByCode(code);
    }

    @Override
    public void addUser(User user) {
        userDao.save(user);
    }
}
