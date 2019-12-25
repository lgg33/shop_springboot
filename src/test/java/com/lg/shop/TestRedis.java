package com.lg.shop;

import com.lg.shop.component.RedisUtil;
import com.lg.shop.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author L
 * @version 1.0
 * @ClassName: TestRedis
 * @date: 2019/12/23 19:56
 * @since JDK 1.8
 */
@SpringBootTest
public class TestRedis {

    @Autowired
    RedisUtil redisUtil;

    @Test
    public void test01() {
        boolean set = redisUtil.set("k1", "u1");
        System.out.println(set);
    }

    @Test
    public void test02() {
        User user = new User();
        user.setUid("afdsgvdsb");
        user.setName("刘备");
        boolean set = redisUtil.set("user1", user);
        System.out.println(set);
    }
}
