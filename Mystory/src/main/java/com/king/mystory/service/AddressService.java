package com.king.mystory.service;

import com.king.mystory.pojo.Address;

import java.util.List;

/**
 * 收获地址业务层接口
 */
public interface AddressService {
    /**
     * 添加用户的收获地址
     * @param uid
     * @param username
     * @param address
     */
    void addNewAddress(Integer uid , String username, Address address);

    /**
     * 根据用户信息查询数据然后返回信息
     * @param uid
     * @return
     */
    List<Address> getByUid(Integer uid);
}
