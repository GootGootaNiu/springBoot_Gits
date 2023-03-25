package com.king.springboot_jq.mapper;

import com.king.springboot_jq.pojo.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest // 表示该类是一个测试类
// @RunWith(SpringRunner.class) 表示启动这个单元测试类 需要传递一个参数 必须是SpringRunner的实列类型

public class UserTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void usermapper() {
        List<User> users =
                userMapper.selectAll();
        for (User user:users
             ) {
            System.out.println(user);
        }
    }

    @Test
    public void selectUsername(){
        User user = userMapper.selectByUsername(18, "王五");
        System.out.println(user);
    }
}
