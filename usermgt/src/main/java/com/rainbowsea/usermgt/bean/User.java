package com.rainbowsea.usermgt.bean;

public class User {

    /**
     * 用户编号
     */
    private Long id;


    /**
     * 用户名
     */
    private String username;


    /**
     * 性别
     * 1 表示男
     * 2 表示女
     */
    private Integer sex;


    /**
     * 邮箱
     */
    private String email;

    public User() {
    }


    public User(Long id, String username, Integer sex, String email) {
        this.id = id;
        this.username = username;
        this.sex = sex;
        this.email = email;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", sex=" + sex +
                ", email='" + email + '\'' +
                '}';
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
