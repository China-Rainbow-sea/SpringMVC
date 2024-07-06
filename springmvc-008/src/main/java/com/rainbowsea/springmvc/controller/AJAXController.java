package com.rainbowsea.springmvc.controller;


import com.rainbowsea.springmvc.bean.User;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.PrintWriter;

//@Controller  // 交给 Spring IOC 容器管理
@RestController  // @Controller + @ResponseBody
public class AJAXController {

    //@GetMapping("/ajax")
    @RequestMapping(value = "/ajax", method = RequestMethod.GET)
 /*   public String ajax(HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        out.print("hell ajax,my name is Spring MVC");

        return null;
    }*/


    //@GetMapping("/ajax")
/*
    public void ajax(HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        out.print("hell ajax,my name is Spring MVC2");
    }
*/


 /*   @GetMapping("/ajax")
    @ResponseBody
    public String ajax() {
        // 当前处理器方法上添加了 @ResponseBoay 注解，
        // 那么这个方法的返回值不再是逻辑视图名称了
        //return "hell ajax,my name is Spring MVC3";
        //return "id:1213,username:lihua,password:123";

        User user = userService.getById(id);

        // JSON 格式的字符串，然后响应到浏览器
        return "{\"username\":\"zhangsan\",\"password\":\"1234\"}";
    }*/



   /* @GetMapping("/ajax")
    @ResponseBody
    public User ajax() {
        // 当前处理器方法上添加了 @ResponseBoay 注解，
        // 那么这个方法的返回值不再是逻辑视图名称了
        //return "hell ajax,my name is Spring MVC3";
        //return "id:1213,username:lihua,password:123";

        User user = new User(1122L, "李华", "123");
        return user;


    }*/


    @GetMapping("/ajax")
    //@ResponseBody
    public User ajax() {
        // 当前处理器方法上添加了 @ResponseBoay 注解，
        // 那么这个方法的返回值不再是逻辑视图名称了
        //return "hell ajax,my name is Spring MVC3";
        //return "id:1213,username:lihua,password:123";

        User user = new User(1122L, "李华", "123");
        return user;


    }
}
