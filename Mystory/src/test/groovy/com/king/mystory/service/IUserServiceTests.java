package com.king.mystory.service;

import com.king.mystory.pojo.User;
import com.king.mystory.service.ex.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;


@SpringBootTest // 表示该类是一个测试类
// @RunWith(SpringRunner.class) 表示启动这个单元测试类 需要传递一个参数 必须是SpringRunner的实列类型
@RunWith(SpringRunner.class)
public class IUserServiceTests {
    // 自动装配
    @Autowired
    private IUserService iUserService;

    @Test
    public void insert(){
        try {
            User user  = new User();
            user.setUsername("小刚");
            user.setPassword("123");
            iUserService.reg(user);
            System.out.println("OK");

        } catch (ServiceException e) {
            // 获取类的对象 在获取类的名称
            System.out.println( e.getClass().getSimpleName());
            // 获取异常的具体描述信息
            System.out.println(e.getMessage());
        }
    }

    /**
     * 用户登录测试
     */
    @Test
    public void  login(){
        User user= iUserService.selectAllService("哈哈","1234");

        System.out.println(user);
    }

    /**
     * 用户修改密码
     */
    @Test
    public void updatePasswordUid(){
        iUserService.changePassword(26,"哈哈","1234","12345");
    }

    /**
     * 根据id 查询用户信息
     */
    @Test
    public void findByid(){
        User byId = iUserService.getById(35);
        System.out.println(byId);
    }


    @Test
    public void update(){
        User user  = new User();
        user.setPhone("234234234243");
        user.setEmail("wwwwwwww");
        user.setGender(1);
        iUserService.changeInfo(33,"管理员",user);
    }

    @Test
    public void updateBy(){
        iUserService.changeAvatar(22,"/sds/sdsd","王五");
    }
}
