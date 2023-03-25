package com.king.mystory.service.impl;

import com.king.mystory.mapper.UserMapper;
import com.king.mystory.pojo.User;
import com.king.mystory.service.IUserService;
import com.king.mystory.service.ex.*;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.UUID;

@Service
public class IUServiceImpl implements IUserService {

    // 自动装配
    @Autowired
    private UserMapper userMapper;


    /**
     * 头像上传
     *
     * @param uid      用户的uid
     * @param avatar   用户头像的路径
     * @param username 用户的名称
     */
    @Override
    public void changeAvatar(Integer uid, String avatar, String username) {
        // 判断用户的数据是不是存在
        User result = userMapper.findByUid(uid);
        // 判断用户的数据是不是等于1
        if (result == null || result.getIsDelete() == 1) {
            throw new UserNotFoundException("用户数据不存在");
        }
        // 经过上面判断表示有数据传入进来 这里就进行修改操作
        Integer rows = userMapper.updateAvatarByUid(uid, avatar, username, new Date());
        if (rows != 1){
            throw  new UpdateException("更新头像时产生未知的异常");
        }
    }


    /**
     * 用户根据id 查询数据返回一个对象
     *
     * @param uid 用户id 用户id
     * @return 返回一个对象信息
     */
    @Override
    public User getById(Integer uid) {
        // 注意：这里拿到用户的uid 之后先去数据库中查询一下看有没有
        User result = userMapper.findByUid(uid);
        // 这里进行判断
        if (result == null) {
            throw new UserNotFoundException("用户数据不存在！！！");
        }
        // 判断完成之后 这里就是用户数据存在的
        // 然后这里就把数据封装成一个对象
        User user = new User();
        // 这里创建一个对象 然后获取传入到对象中的数据信息
        user.setUsername(result.getUsername());
        user.setPhone(result.getPhone());
        user.setEmail(result.getEmail());
        user.setGender(result.getGender());
        // 拿到数据之后 通过return 返回数据
        return user;
    }

    /**
     * 修改用户的个人信息
     *
     * @param uid      uid 用户的id 根据用户的id 查询用户的信息
     * @param username 用户名称不能修改
     * @param user     用户对象数据
     */
    @Override
    public void changeInfo(Integer uid, String username, User user) {
        // 第一步： 这里需要判断一下用户 有没有数据
        User result = userMapper.findByUid(uid);
        // 判断
        if (result == null || result.getIsDelete() == 1) {
            throw new UserNotFoundException("用户数据不存在");
        }
        // 注意：这里取出的数据 是用户传入进来的数据
        // 如果有就去调用这些数据
        user.setUid(uid);
//        user.setUsername(username);
        user.setModifiedUser(username);
        user.setModifiedTime(new Date());
        // 注意：用户输入的数据 这里调用用进行修改
        Integer integer = userMapper.updateInfoByUid(user);
        // 这里判断用户的数据 如果返回1 就表示正常 如果不是则报异常
        if (integer != 1) {
            throw new UpdateException("更新数据时产生未知的异常");
        }
    }


    /**
     * 用户修改密码操作
     *
     * @param uid         uid 这是从session中获取的
     * @param username    username 这个也是从session中获取的
     * @param oldPassword 用户输入的旧密码
     * @param newPassword 用户输入的新密码
     */
    @Override
    public void changePassword(Integer uid, String username, String oldPassword, String newPassword) {
        // 第一步：先拿着uid 去数据库中查找
        User result = userMapper.selectUid(uid);
        // 在判断 uid 是否为空 用户有没有删除
        if (result == null || result.getIsDelete() == 1) {
            throw new UserNotFoundException("用户数据不存在");
        }
        //第三步： 判断完成之后这里就判断用户输入的原始密码和数据库中的密码是否一样
        String ordM5Password =
                getMD5Password(oldPassword, result.getSalt());
        // 第四步：在去判断 用户输入的原密码和修改的密码是否一样
        if (!result.getPassword().equals(ordM5Password)) {
            throw new PasswordNotMatchException("原始密码错误");
        }
        // 从查询结果集中取出盐值
//        String salt = result.getSalt();
        // 第五步：将设置的新密码 设置到数据库中 先进行加密在设置到数据库中去
        String newMd5Password =
                getMD5Password(newPassword, result.getSalt());
        // 第六步：加密完成之后 在进行数据的传递
        Integer integer = userMapper.updatePasswordByUid(uid, newMd5Password, username, new Date());
        // 这里判断  如果integer =1 数据添加成功
        if (integer != 1) {
            throw new UpdateException("更新数据的时候产生位置的异常");
        }

    }


    /**
     * 用户登录的功能
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public User selectAllService(String username, String password) {
        // 第一步：用户输入的用户名查询用户名是否存在
        User byUsername = userMapper.findByUsername(username);
        // 第二步：判断数据库中有没有这个 用户输入的
        if (byUsername == null) {
            throw new UserNotFoundException("用户名数据不存在,请从新输入!!!!!");
        }
        //第三步：检查用户的密码是否匹配
        //1, 先获取到数据库中的加密之后的密码
        String oldPassword = byUsername.getPassword();
        //2, 和用户传递过来的密码进行比较
        //2.1 先获取盐值：上一次在注册时自动生成的盐值
        String salt = byUsername.getSalt();
        //2.2 将用户的密码按照相同的md5 算法的规则进行加密
        String md5Password = getMD5Password(password, salt);
        //3, 将密码进行比较
        if (!md5Password.equals(oldPassword)) {
            throw new PasswordNotMatchException("用户密码错误");
        }

        // 判读is_delete 字段的值是否为1 表示被标记为删除
        if (byUsername.getIsDelete() == 1) {
            throw new UserNotFoundException("用户数据不存在");
        }

        //数据的查询操作
//        User user = userMapper.findByUsername(username);
        // 调用mapper 层的findByUsername 来查询用户的数据提升性能
        User user = new User();
        user.setUid(byUsername.getUid());
        user.setUsername(byUsername.getUsername());
        user.setAvatar(byUsername.getAvatar());
        // 当前的用户数据返回
        return user;
    }


    /**
     * 用户注册的功能
     *
     * @param user 接收用户的user
     */
    @Override
    public void reg(User user) {
        //先拿到用户的用户名去数据库中查找 是否为null
        String username = user.getUsername();
        // 这里根据userName 去数据库中查找
        User byUsername = userMapper.findByUsername(username);
        // 这里通过if 对数据进行比较  用户名不为空的话就提示用户
        if (byUsername != null) {
            // 抛出异常提醒用户
            // 注意 ： throw 执行完成之后程序就不会在去执行了
            throw new UsernameDuplicatedException("用户名称已被已被占用");
        }
        /**
         * 密码加密处理的实现：md5 算法的形式：34hhhhhh-jjjjjj121-yyyyyy222-hyyyyv-22222
         *  (串 +  password + 串) ---- md5算法进行加密 连续加载三次
         *  盐值 + password + 盐值 ----盐值就是一个随机的字符串
         */
        // 获取用户输入的密码
        String oldPassword = user.getPassword();
        // 获取盐值 (随机生成一个盐值)
        String salt = UUID.randomUUID().toString().toUpperCase();
        // 补全数据：盐值的记录
        user.setSalt(salt);
        // 将密码和盐值作为一个整体
        String md5Password = getMD5Password(oldPassword, salt);
        // 将加密之后的密码重新补全设置到user 对象中
        user.setPassword(md5Password);

        //这里把创建人的信息给完善一些
        // 补全数据 is_delete 未删除0  删除了1
        user.setIsDelete(0);
        // 补全日志的创建人信息
        user.setCreatedUser(user.getUsername());
        // 最后修改的执行人
        user.setModifiedUser(user.getUsername());
        // 创建日期的
        Date date = new Date();
        // 日志第一次创建的时间
        user.setCreatedTime(date);
        // 最后修改的时间
        user.setModifiedTime(date);


        // 如果用户名称为空的话 就可以让用户进行注册
        Integer insert = userMapper.insert(user);
        // 用户注册的时候可能会发生未知的异常 这里进行处理一下
        if (insert != 1) {
            //抛出异常
            throw new InsertException("未知异常注册失败");
            // 注意：注册的时候根据表进行分析 有的数据需要提前使用
        }
    }


    /**
     * 使用Md5  对密码进行加密的功能
     */
    private String getMD5Password(String password, String salt) {
        for (int i = 0; i < 3; i++) {
            password = DigestUtils.md5DigestAsHex((salt + password + salt).getBytes()).toUpperCase();
        }
        return password;
    }

}

































