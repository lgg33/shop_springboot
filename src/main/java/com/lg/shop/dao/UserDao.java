package com.lg.shop.dao;

import com.lg.shop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author L
 * @version 1.0
 * @ClassName: UserDao
 * @date: 2019/12/22 14:02
 * @since JDK 1.8
 */
@Repository
public interface UserDao extends JpaRepository<User, String> {
    User findByCode(String code);

    User findByUsername(String username);

    User findByUsernameAndPassword(String username, String password);
}
