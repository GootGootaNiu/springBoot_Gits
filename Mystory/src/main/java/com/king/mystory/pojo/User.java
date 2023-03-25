package com.king.mystory.pojo;

import java.io.Serializable;
import java.util.Objects;

/**
 * 集成这个类 BaseEntity
 * Serizlizable 序列化  流的序列化
 */
public class User extends BaseEntity implements Serializable {
    //    uid INT AUTO_INCREMENT COMMENT '用户id',
//    username VARCHAR(20) NOT NULL UNIQUE COMMENT '用户名',
//    password CHAR(32) NOT NULL COMMENT '密码',
//    salt CHAR(36) COMMENT '盐值',
//    phone VARCHAR(20) COMMENT '电话号码',
//    email VARCHAR(30) COMMENT '电子邮箱',
//    gender INT COMMENT '性别:0-女，1-男',
//    avatar VARCHAR(50) COMMENT '头像',
//    is_delete INT COMMENT '是否删除：0-未删除，1-已删除',

    private Integer uid; // 用户的id
    private String username; // 用户的 账户
    private String password; // 用户的密码
    private String salt;   //  盐值 是用来加密用的
    private String phone; // 用户电话
    private String email; // 用户邮箱
    private Integer gender;// 0-女 1-男
    private String avatar; // 头像
    private Integer isDelete; // 删除用户信息

    public User() {
    }

    public User(Integer uid, String username, String password, String salt, String phone, String email, Integer gender, String avatar, Integer isDelete) {
        this.uid = uid;
        this.username = username;
        this.password = password;
        this.salt = salt;
        this.phone = phone;
        this.email = email;
        this.gender = gender;
        this.avatar = avatar;
        this.isDelete = isDelete;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", gender=" + gender +
                ", avatar='" + avatar + '\'' +
                ", isDelete=" + isDelete +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return Objects.equals(uid, user.uid) && Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(salt, user.salt) && Objects.equals(phone, user.phone) && Objects.equals(email, user.email) && Objects.equals(gender, user.gender) && Objects.equals(avatar, user.avatar) && Objects.equals(isDelete, user.isDelete);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), uid, username, password, salt, phone, email, gender, avatar, isDelete);
    }
}
