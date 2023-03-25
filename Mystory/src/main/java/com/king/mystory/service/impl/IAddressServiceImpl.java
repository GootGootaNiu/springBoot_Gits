package com.king.mystory.service.impl;

import com.king.mystory.mapper.AddressMapper;
import com.king.mystory.pojo.Address;
import com.king.mystory.service.AddressService;
import com.king.mystory.service.DistrictService;
import com.king.mystory.service.ex.AddressCountLimitException;
import com.king.mystory.service.ex.InsertException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class IAddressServiceImpl implements AddressService {

    @Autowired
    private AddressMapper addressMapper;

    /**
     * 添加 用户的收获地址的业务层依赖于 DistrictService的接口
     */
    @Autowired
    private DistrictService districtService;


    @Value("${user.address.max-count}")
    private int maxCount;



    @Override
    public void addNewAddress(Integer uid, String username, Address address) {
        // 判读用户的uid 是否有值 是否大于 20
        Integer count = addressMapper.countByUid(uid);
        //判断一下
        // 注意：这里有时候用户让修改最大上线收获地址
        // 这里可以定义一个常量用来读取配置文件中的数据
//        if (count >= 20){
        // maxCount 自定义读取配置文件中的内容
        if (count > maxCount){
            throw new AddressCountLimitException("用户收获地址超出上限");
        }

        // uid isDefault 拿到用户的uid 检查用户的uid 有没有默认值
        address.setUid(uid);
        // 判断 0 ? 1 : 0 ;// 1 表示默认 0 表示不是默认
        Integer isDefault = count ==  0 ? 1 : 0 ;
        address.setIsDefault(isDefault);

        // 在对象中拿到数据
        String provinceName = districtService.findByCode(address.getProvinceCode());
        String cityName = districtService.findByCode(address.getCityCode());
        String areaName = districtService.findByCode(address.getAreaCode());
//            String provinceName = districtService.findByCode(address.getProvinceCode());

        // 这里把从对象中拿到的值 赋值给 对象中去
        address.setProvinceName(provinceName);
        address.setCityName(cityName);
        address.setAreaName(areaName);




        // 是谁添加收获地址的人给找出来
        address.setCreatedUser(username); // 创建人
        address.setModifiedUser(username); // 修改人信息
        address.setCreatedTime(new Date()); // 创建时间
        address.setModifiedTime(new  Date()); //修改时间信息

        // 插入收获地址的方法
        Integer rows = addressMapper.insert(address);
        if (rows != 1){
            throw new InsertException("插入用户的收获地址产生未知的异常");

        }
    }

    /**
     * 根据uid  查询用户信息然后把数据返回到前端页面上去
     * @param uid 用户查询条件
     * @return
     */
    @Override
    public List<Address> getByUid(Integer uid) {
        List<Address> list= addressMapper.findByUid(uid);

        //通过for 循环遍历 给这里面的属性赋值
        for (Address address:list) {
            address.setAid(null);
            address.setUid(null);
            address.setProvinceCode(null);
            address.setCityCode(null);
            address.setTel(null);
            address.setIsDefault(null);
            address.setCreatedTime(null);
            address.setCreatedUser(null);
            address.setModifiedTime(null);
            address.setModifiedUser(null);
        }
        return list;
    }
}
