package com.rainbowsea.springmvc.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller  // 交给 Spring IOC 容器管理
public class IndexController {


    @RequestMapping("/index")
    public String toIndex() {
        System.out.println("IndexController#toIndex()  ---> 处理器方法执行了");
        return "index";
    }


    @RequestMapping("ok")
    public String toOK() {
        System.out.println("IndexController#OK() ---> 处理器方法执行了");
        return "ok";
    }
}
