package com.rainbowsea.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller // 交给 Spring IOC 容器管理
public class IndexController {


    @RequestMapping("/")
    public String index() {
        return "index";
    }
}
