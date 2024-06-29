package com.rainbowsea.springmvc.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {
    @RequestMapping("/test2")
    public String test2() {
        // 这个处理方法没有编写任何业务逻辑代码，只是未来完成一个普通的页面跳转
        return "test2";
    }

    @RequestMapping("/test")
    public String test() {
        // 这个处理方法没有编写任何业务逻辑代码，只是未来完成一个普通的页面跳转
        return "test";
    }
}
