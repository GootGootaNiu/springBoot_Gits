package com.king.mystory.mapper;

import com.king.mystory.pojo.Address;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface AddressMapper {

    /**
     * 查询用户的全部信息
     *
     * @return
     */
    List<Address> selectAll();

    /**
     * 插入用户信息
     *
     * @param address 根据对象插入用户信息
     * @return 返回插入的记录条数 1 条
     */
    Integer insert(Address address);


    /**
     * 查询用户根据uid 统计收获地址的数量的
     *
     * @param uid 用户的uid
     * @return 当前用户的收获地址总数
     */
    Integer countByUid(Integer uid);

    /**
     * 根据用户的uid 查询用户的数据
     *
     * @param aid 用户的uid
     * @return
     */
    Address selectByAddress(Integer aid);

    /**
     * 根据用户的 uid 查询用户的收货地址数据
     *
     * @param uid 用户id
     * @return 收获地址数据
     */
    List<Address> findByUid(Integer uid);


    /**
     * 逻辑判断 根据用的 aid 查询出用户的默认信息
     * 在使用 uid 来吧用户的数据设置成不是默认的信息
     * 在用户 update 修改语句修改用户的aid和用户的其他信息
     */

    /**
     * 根据aid 查询收获地址数据
     *
     * @param aid 收获地址aid
     * @return 收获地址数据，如果没有找到返回null值
     */
    Address findByAid(Integer aid);

    /**
     * 根据用户的uid 值来修改用户的收获地址设置为非默认
     * 就是用来设置收获地址的默认值的
     *
     * @param uid 用户的id 值
     * @return 受影响的行数
     */
    Integer updateNonDefault(Integer uid);

    /**
     * 用来修改用户收获地址的默认值的
     * @param aid
     * @param modifiedUser
     * @param modifiedTime
     * @return
     */
    Integer updateDefaultByAid(@Param("aid") Integer aid,
                               @Param("modifiedUser") String modifiedUser,
                               @Param("modifiedTime") Date modifiedTime);


}
