package com.king.mystory.service;

import com.king.mystory.pojo.Address;
import com.king.mystory.pojo.District;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@SpringBootTest // 表示该类是一个测试类
// @RunWith(SpringRunner.class) 表示启动这个单元测试类 需要传递一个参数 必须是SpringRunner的实列类型
@RunWith(SpringRunner.class)
public class DistrictServiceTests {
    // 自动装配
    @Autowired
    private DistrictService districtService;

    @Test
    public void select() {
        List<District> byParen =
                districtService.getByParen("610000");
        for (District byParens : byParen
        ) {
            System.out.println(byParens);
        }
    }
}
