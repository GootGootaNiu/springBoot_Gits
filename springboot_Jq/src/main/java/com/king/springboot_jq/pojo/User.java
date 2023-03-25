package com.king.springboot_jq.pojo;

public class User {
    private Integer id;
    private String username;
    private Integer age;
    private String  sex;
    private String heart;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", heart='" + heart + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getHeart() {
        return heart;
    }

    public void setHeart(String heart) {
        this.heart = heart;
    }

    public User() {
    }

    public User(Integer id, String username, Integer age, String sex, String heart) {
        this.id = id;
        this.username = username;
        this.age = age;
        this.sex = sex;
        this.heart = heart;
    }
}
