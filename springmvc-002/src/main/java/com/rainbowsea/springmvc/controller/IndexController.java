package com.rainbowsea.springmvc.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller // 表示交给 Spring IOC 容器管理
public class IndexController {


    @RequestMapping("/")
    public String index() {
        return "index";
    }











    @RequestMapping("/first")  // 注意要加 / 斜杆
    public String first() {
        return "first";
    }
}
