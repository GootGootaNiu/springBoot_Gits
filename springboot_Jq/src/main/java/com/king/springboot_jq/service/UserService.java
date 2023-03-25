package com.king.springboot_jq.service;

import com.king.springboot_jq.pojo.User;

import java.util.List;

public interface UserService {
    List<User> getAll();

    User getSelsectUname(Integer age,String username);


    int insertAll(User user);

    int deleteById(Integer id);

    User selectById(Integer id);

    int updateById(User user);
}
