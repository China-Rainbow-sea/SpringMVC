package com.rainbowsea.springmvc.controller;


import com.rainbowsea.springmvc.bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller  // 交给 Spring IOC 容器进行管理
public class UserController {

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String getAll() {
        System.out.println("正在查询所有用户信息...");
        return "ok";
    }


    //@RequestMapping(value = "/user/{占位符}",method = RequestMethod.GET)
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public String getById(@PathVariable(value = "id") String id) {
        System.out.println("正在根据用户 id 查询用户信息...用户 id 是" + id);

        return "ok";
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public String save(User user) {
        System.out.println("正在保存用户信息");
        System.out.println(user);
        return "ok";
    }

    @RequestMapping(value = "/user", method = RequestMethod.PUT)
    public String modify(User user) {
        System.out.println("正在修改用户信息" + user);

        return "ok";
    }


    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public String del(@PathVariable(value = "id") String id) {
        System.out.println("正删除用户 : " + id);

        return "ok";
    }

}
