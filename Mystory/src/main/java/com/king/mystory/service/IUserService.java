package com.king.mystory.service;

import com.king.mystory.pojo.User;

public interface IUserService {

    /**
     * 用户注册功能的实现
     *
     * @param user 接收用户的user
     */
    void reg(User user);

    /**
     * 根据用户名和密码查询用户信息
     *
     * @param username
     * @param password
     * @return
     */
    User selectAllService(String username, String password);

    /**
     * 注意：业务层进行的逻辑判读
     * @param uid 根据用户的uid 去查询数据
     * @param username 用户的名称
     * @param oldPassword 用户输入的密码
     * @param newPassword 用户
     */
    void changePassword(Integer uid,
                        String username,
                        String oldPassword,
                        String newPassword);

    /**
     * 根据用户的id 查询用户的数据
     * @param uid 用户id
     * @return 返回用户的数据
     */
    User getById(Integer uid);


    /**
     * 更新用户的数据操作
     * @param uid uid 用户的id
     * @param username 用户的名称
     * @param user 用户对象数据
     */
    void changeInfo(Integer uid,String username,User user);

    /**
     * 修改用户的头像
     * @param uid 用户的uid
     * @param avatar 用户头像的路径
     * @param username 用户的名称
     */
    void changeAvatar(Integer uid ,String avatar,String username);
}
