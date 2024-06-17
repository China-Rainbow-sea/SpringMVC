package com.rainbowsea.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller // 交给Spring IOC 容器管理
@RequestMapping(value = "/user")
public class UserController {


    //@RequestMapping(value = "/user/detail")
    @RequestMapping(value = "/detail")
    public String userDetail() {
        return "user_detail";
    }
}
