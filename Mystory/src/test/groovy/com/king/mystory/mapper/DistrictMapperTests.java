package com.king.mystory.mapper;

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
public class DistrictMapperTests {

    @Autowired
   private DistrictMapper districtMapper;

    /**
     * 查询用户的省份信息
     */
    @Test
    public void select(){
        List<District> byParent = districtMapper.getByParent("110100");
        for (District by: byParent) {
            System.out.println(by);
        }
    }

    /**
     * 查询用户市对应的信息
     */
    @Test
    public void findByCode(){
        String byCode = districtMapper.findNameByCode("610000");
        System.out.println(byCode);
    }


}
