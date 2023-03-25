package com.king.mystory.mapper;

import com.king.mystory.pojo.Address;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest // 表示该类是一个测试类
// @RunWith(SpringRunner.class) 表示启动这个单元测试类 需要传递一个参数 必须是SpringRunner的实列类型
@RunWith(SpringRunner.class)
public class AddressMapperTests {

    @Autowired
    private AddressMapper addressMapper;

    @Test
    public void address() {
        List<Address> addresses =
                addressMapper.selectAll();
        for (Address addList: addresses) {
            System.out.println(addList);
        }
    }

    @Test
    public void insert(){
        Address address = new Address();
        address.setAid(null);
        address.setUid(10);
        address.setPhone("159241662");
        address.setName("女朋友");
        address.setProvinceName("sdf");
        Integer insert = addressMapper.insert(address);
        System.out.println(insert);
    }

    @Test
    public void select(){
        Integer integer = addressMapper.countByUid(2);
        System.out.println(integer);
    }

    @Test
    public void findByAll(){
        Address address = addressMapper.selectByAddress(3);
        System.out.println(address);
    }
    @Test
    public void selectss(){
        List<Address> list = addressMapper.findByUid(35);
        System.out.println(list);
    }
}
