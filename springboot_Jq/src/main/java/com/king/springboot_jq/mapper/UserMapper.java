package com.king.springboot_jq.mapper;

import com.king.springboot_jq.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    // 查询用户的全部信息

    /**
     * 查询用户全部信息
     * @return
     */
     List<User> selectAll();

    /**
     * 根据用户的age 查询用户的信息
     * @param age
     * @param username
     * @return
     */
     User selectByUsername(Integer age ,String username);

    /**
     * 添加数据返回对象信息
     */
    /**
     * 添加数据
     * @param user
     * @return
     */
    int insertAll(User user);


    /**
     * 根据id 删除用户信息
     * @param id
     * @return
     */
    int deleteById(Integer id);

    /**
     * 根据id 查询用户信息
     * @param id
     * @return
     */
    User selectById(Integer id);


    /**
     * 根据用户修改数据
     * @param user
     * @return
     */
    int updateById(User user);
}
