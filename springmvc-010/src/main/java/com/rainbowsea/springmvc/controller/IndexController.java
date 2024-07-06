package com.rainbowsea.springmvc.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller  // 交给 Spring IOC 容器管理
public class IndexController {


    //@RequestMapping(value = "/index", method = RequestMethod.POST)
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String toIndex() {

        String s = null;
        s.toString();
        return "index";
    }
}
