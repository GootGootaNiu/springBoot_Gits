package com.king.springboot_jq.service.impl;

import com.king.springboot_jq.mapper.UserMapper;
import com.king.springboot_jq.pojo.User;
import com.king.springboot_jq.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public List<User> getAll() {
        return userMapper.selectAll();
    }

    @Override
    public User getSelsectUname(Integer age, String username) {
        return userMapper.selectByUsername(age,username);
    }

    @Override
    public int insertAll(User user) {
        return userMapper.insertAll(user);
    }

    @Override
    public int deleteById(Integer id) {
        return userMapper.deleteById(id);
    }

    @Override
    public User selectById(Integer id) {
        return userMapper.selectById(id);
    }

    @Override
    public int updateById(User user) {
        return userMapper.updateById(user);
    }
}
