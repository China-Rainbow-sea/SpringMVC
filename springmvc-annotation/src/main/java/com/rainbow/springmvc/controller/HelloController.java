package com.rainbow.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller  // 交给 Spring IOC 容器管理
public class HelloController {

    @RequestMapping(value = "/hello",method = RequestMethod.POST)
    public String hello() {
        return "hello";
    }
}
