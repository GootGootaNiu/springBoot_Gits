package com.king.mystory.mapper;

import com.king.mystory.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.Date;

@Mapper
public interface UserMapper {
    /**
     * 注册功能
     * 插入用户的数据
     *
     * @param user 用户输入的数据
     * @return 受影响的行数 （增删改查 都受影响的行数作为返回值 可以根据返回值来判断是否执行了操作）
     */
    Integer insert(User user);

    /**
     * 根据 username 查询用户的数据是否存在
     *
     * @param username 用户名
     * @return 如果找到对应的用户则返回这个用户的数据 如果没有找到则返回null 值
     */
    User findByUsername(String username);

    /**
     * 根据账户或者密码查询数据
     * @param username
     * @param password
     * @return
     */
//    User selectAll(String username,String password);


    /**
     * 根据用户的id 查询用户的数据
     *
     * @param uid 用户的id
     * @return 如果找到则返回对象 如果没有找到则返回null值
     */
    User selectUid(Integer uid);

    /**
     * 根据用户的uid 来修改用户密码
     *
     * @param uid          用户的id
     * @param password     用户输入的密码
     * @param modifiedUser 修改人信息
     * @param modifiedTime 修改人时间
     * @return 返回值为受影响的行数
     */
    Integer updatePasswordByUid(Integer uid,
                                String password,
                                String modifiedUser
            , Date modifiedTime);

    /**
     * 根据用户id 查询用户数据
     *
     * @param uid
     * @return
     */
    User findByUid(Integer uid);

    /**
     * 根据用户的数据修改用户的信息
     *
     * @param user 用户数据
     * @return 返回受影响的行数
     */
    Integer updateInfoByUid(User user);

    /** @Param("SQL映射文件中#{} 占位符的变量名")：解决的问题 当sql 语句的占位符
     * 和映射的接口方法参数名不一致时 需要将某个参数强行注入到某个占位符 然后
     * 就是用@param这个注解
     * 根据用户的uid  去修改用户的头像信息
     * @param uid 用户uid
     * @param avatar 用户头像信息
     * @param modifiedUser 修改人信息
     * @param modifiedTime 修改时间信息
     * @return
     */
    Integer updateAvatarByUid(Integer uid,
                              String avatar,
                              String modifiedUser,
                              Date modifiedTime);
}




































