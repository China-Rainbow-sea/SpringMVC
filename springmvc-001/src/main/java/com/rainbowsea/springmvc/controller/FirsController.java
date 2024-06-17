package com.rainbowsea.springmvc.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller // 该注解表示,将该类交给 Spring IOC 容器管理
public class FirsController {


    // 请求映射
    // 这个方法是一个实例方法
    // 这个方法目前返回一个String 字符串
    // 返回值代表一逻辑视图名称,一个逻辑视图名称,会对应上一个真正的物理上的文件
    @RequestMapping(value = "/test")
    public String hehe() {
        // 返回一个逻辑视图名称
        return "first";
    }



    @RequestMapping(value = "/heihei")
    public String haha() {
        // 处理核心业务
        // 返回一个逻辑视图名称
        return "other";
    }



    @RequestMapping("/")
    public String toIndex() {
        // 返回逻辑视图名称
        return "index";
    }

}
