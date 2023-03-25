package com.king.mystory.service;

import com.king.mystory.pojo.Address;
import com.king.mystory.pojo.User;
import com.king.mystory.service.ex.ServiceException;
import com.king.mystory.service.impl.IAddressServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest // 表示该类是一个测试类
// @RunWith(SpringRunner.class) 表示启动这个单元测试类 需要传递一个参数 必须是SpringRunner的实列类型
@RunWith(SpringRunner.class)
public class IAddressServiceTests {
    // 自动装配
    @Autowired
    private AddressService addressService;
    @Test
    public void insert(){
      Address address = new Address();
      address.setUid(16);
      address.setPhone("1232313");
      address.setName("男朋友");
      addressService.addNewAddress(23,"哈哈",address);
    }
}
