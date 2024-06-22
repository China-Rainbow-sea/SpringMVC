package com.rainbowsea.springmvc.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;


@SessionAttributes(value = {"x","y","testSessionScope"}) // 标注 x 和 y 都是存放到 session 域中，而不是 request域
@Controller // 交给 Spring IOC 容器管理
public class SessionScopeTestController {


    @RequestMapping("/testSessionServletAPI")
    public String testServletAPI(HttpSession session) {
        // 处理核心业务...
        //将数据存储到 Session中
        session.setAttribute("testSessionScope","在Spring MVC 当中使用原生 Servlet API 完成 session 域数据共享");
        // 返回逻辑视图（这是一个转发的行为）
        return "ok";
    }


    @RequestMapping(value = "/testSessionAttributes")
    public String testSessionAttributes(ModelMap modelMap) {
        // 处理业务
        // 将数据存储到 Session域当中
        modelMap.addAttribute("testSessionScope","在Spring MVC 当中使用@SessionAttributes 注解完成 session 域数据共享");
        modelMap.addAttribute("x","李华");
        modelMap.addAttribute("y","小红");
        return "ok";
    }
}
