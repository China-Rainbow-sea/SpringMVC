package com.rainbowsea.springmvc.controller;


import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.PrintWriter;

@Controller  // 交给 Spring IOC 容器管理
public class AJAXController {


    @RequestMapping(value = "/ajax", method = RequestMethod.GET)
    @ResponseBody   // 由于你使用了 @ResponseBody 注解
    public String ajax() {
        // 当前处理器方法上添加了 @ResponseBoay 注解，
        // 那么这个方法的返回值不再是逻辑视图名称了
        // 而是作为响应协议的响应体进行响应。

        // JSON 格式的字符串，然后响应到浏览器
        return "{\"username\":\"zhangsan\",\"password\":\"1234\"}";
        //return "hell ajax,my name is Spring MVC";
    }










/*
    @RequestMapping(value = "/ajax",method = RequestMethod.GET)
    public void ajax(HttpServletResponse response) throws IOException {
        PrintWriter writer = response.getWriter();
        writer.print("hell ajax,my name is Spring MVC");

    }*/






  /*  @RequestMapping(value = "/ajax",method = RequestMethod.GET)
    public String ajax(HttpServletResponse response) throws IOException {
        PrintWriter writer = response.getWriter();
        writer.print("hell ajax,my name is Spring MVC");

        return null;
    }*/
}
