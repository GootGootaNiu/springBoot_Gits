package com.king.mystory.mapper;

import com.king.mystory.pojo.Address;
import com.king.mystory.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@SpringBootTest // 表示该类是一个测试类
// @RunWith(SpringRunner.class) 表示启动这个单元测试类 需要传递一个参数 必须是SpringRunner的实列类型
@RunWith(SpringRunner.class)
public class UserMapperTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void insert() {
        User user = new User();
        user.setUsername("小明");
        user.setPassword("123");
        Integer insert = userMapper.insert(user);
        System.out.println(insert);
    }

    /**
     * 根据用户查询数据
     */
    @Test
    public void selectByUserName() {
        String user = "张三";
        User byUsername = userMapper.findByUsername(user);
        System.out.println(byUsername);
    }

    /**
     * 根据用户的id 查询用户的信息
     */
    @Test
    public void selectUid() {
        User user = userMapper.selectUid(10);
        System.out.println(user);
    }

    /**
     * 根据uid 修改用户的密码信息
     */
    @Test
    public void updatePasswordandUid() {
        Integer integer = userMapper.updatePasswordByUid(3, "1234", "张三", new Date());
        System.out.println(integer);
    }

    @Test
    public void updateInfoByUid() {
        User user = new User();
        user.setUid(28);
        user.setPhone("123123131");
        user.setEmail("290798333@qq.com");
        user.setGender(1);
        Integer integer = userMapper.updateInfoByUid(user);
        System.out.println(integer);
    }

    /**
     * 根据id 查询用户的数据
     */
    @Test
    public void findByUid() {
        User byUid = userMapper.findByUid(35);
        System.out.println(byUid);
    }

    @Test
    public void updateTouXiang() {
        userMapper.updateAvatarByUid(22, "/uplss/jpd", "王五", new Date());
    }


}





































