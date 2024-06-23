package com.rainbowsea.springmvc.controller;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller // 交给Spring IOC 容器管理
public class RequestScopeTestController {



    @RequestMapping(value = "/testModelAndView")
    public ModelAndView testModelAndView() {
        // 创建模型视图对象
        ModelAndView modelAndView = new ModelAndView();
        // 给模型视图对象绑定数据
        modelAndView.addObject("testRequestScope", "在SpringMVC当中使用 ModelAndView 类完成 request 域数据共享");

        // 给模型视图对象 绑定视图（绑定逻辑视图名称）
        modelAndView.setViewName("ok");

        // 返回模型视图对象
        return modelAndView;
    }


    @RequestMapping(value = "/testModelMap")
    public String testModelMap(ModelMap modelMap) {
        // 向 request 域当中存储数据
        modelMap.addAttribute("testRequestScope", "在Spring MVC 当中 ModelMap类完成request 域数据共享");
        System.out.println(modelMap);
        System.out.println(modelMap.getClass().getName());
        return "ok";
    }

    @RequestMapping(value = "/testMap")
    public String testMap(Map<String, Object> map) {

        // 向 request 域当中的存储数据
        map.put("testRequestScope", "在Spring MVC 当中使用 Map接口完成 request 域数据共享");
        System.out.println(map);
        System.out.println(map.getClass().getName());
        // 转发
        return "ok";
    }




    @RequestMapping(value = "/testModel")
    public String testModel(Model model) {
        // 向 request 域当中绑定数据
        model.addAttribute("testRequestScope", "在SpringMVC 当中使用 Model 接口完成 request 域数据共享");
        System.out.println(model);
        System.out.println(model.getClass().getName());
        // 转发
        return "ok";
    }





    // request 请求域
    @RequestMapping("/testServletAPI")
    public String testServletAPI(HttpServletRequest request) {

        // 将共享的数据存储到 request域当中
        // 跳转视图，在视图页面将request域中的数据取出，这样就完成了，Controller和View在同一个请求当中两个组件之间的数据共享

        // 将共享的数据存储到request域当中
        request.setAttribute("testRequestScope", "在SpringMVC当中使用原生Servlet API 完成 request域的数据共享");
        System.out.println(request);
        System.out.println(request.getClass().getName());

        // 跳转视图，在视图页面将 request 域中的数据取出来，这样就完成了，Controller 和 View 在同
        // 一个请求当中两个组件之间数据的共享

        // 注意：这个是跳转，默认情况下是，转发的方式，（转发 forward 是一次请求）
        // 这个返回的是一个逻辑视图名称，经过视图解析器解析，变成物理视图名称，/WEB-INF/templates/ok.html
        return "ok";
    }

















}
