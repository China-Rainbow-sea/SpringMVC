package com.rainbowsea.springmvc.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller // 交给 Spring IOC 容器进行管理
public class ForwardController {

    @RequestMapping("/a")
    public String toA() {
        // 返回的是一个逻辑视图名称
        //return "a";

        // 采用SpringMVC的转发方式跳转到 /b
        // 转发的时候，格式有特殊要求，return "forward:下一个资源的路径"
        // 这种方式就不是逻辑视图名称了
        //return "forward:/b"; // 创建InternalResourceView对象

        // 这个使用较多，重定向,url 中会显示资源路径
        return "redirect:/b";
    }


    @RequestMapping("/b")
    public String toB() {
        // 返回的是一个逻辑视图名称
        return "b";
    }
}
