package com.rainbowsea.springmvc.pojo;

import java.util.Arrays;

public class User {
    private Long id;
    private String a;  // 本质上调用的是 对应的 setXXX 方法进行赋值操作的
    private String password;
    private Integer sex;
    private String[] interest;
    private String intro;
    private Integer age;


    public User() {
    }


    public User(Long id, String username, String password, Integer sex, String[] interest, String intro, Integer age) {
        this.id = id;
        this.a = username;
        this.password = password;
        this.sex = sex;
        this.interest = interest;
        this.intro = intro;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + a + '\'' +
                ", password='" + password + '\'' +
                ", sex=" + sex +
                ", interest=" + Arrays.toString(interest) +
                ", intro='" + intro + '\'' +
                ", age=" + age +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return a;
    }

    public void setUsername(String username) {
        this.a = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String[] getInterest() {
        return interest;
    }

    public void setInterest(String[] interest) {
        this.interest = interest;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
