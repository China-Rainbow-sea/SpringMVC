package com.rainbowsea.springmvc.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller  // 将该类交给 Spring IOC 容器管理
@RequestMapping(value = "/user")
public class UserController {


    @RequestMapping(value = "/detail") // 请求映射
    public String userDetail() {
        // 逻辑视图映射
        return "user_detail";
    }
}
