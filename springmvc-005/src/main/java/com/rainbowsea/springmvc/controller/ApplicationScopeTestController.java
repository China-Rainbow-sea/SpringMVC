package com.rainbowsea.springmvc.controller;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller // 交给 Spring IOC 容器管理
public class ApplicationScopeTestController {


    @RequestMapping("/testApplicationScope")
    public String testApplicationScope(HttpServletRequest request) {
        // 将数据存储到application域当中
        // 获取application对象，其实就是获取 ServletContext对象
        // 怎么获取 ServletContext对象/通过 request,通过 session都可以用
        ServletContext application = request.getServletContext();
        application.setAttribute("testApplicationScope", "在Spring MVC 中使用 Servlet API中实现application域共享");
        return "ok";
    }
}
