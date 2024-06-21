package com.rainbowsea.springmvc.controller;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;

@Controller // 交给 Spring IOC 容器管理
public class UserController {

    @RequestMapping(value = "/")
    public String toRegister() {
        // 返回一个逻辑视图
        return "register";
    }


    //@RequestMapping(value = "/user/reg",method = RequestMethod.POST)
    public String register(HttpServletResponse response, HttpServletRequest request, HttpSession session) {
        // HttpServletResponse，HttpServletRequest，HttpSession 都属于原生 Servlet API
        System.out.println(request);
        System.out.println(response);
        System.out.println(session);

        // 获取请求提交的数据:
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String sex = request.getParameter("sex");
        String[] interests = request.getParameterValues("interest");
        String intro = request.getParameter("intro");


        System.out.println(username);
        System.out.println(password);
        System.out.println(sex);
        System.out.println(Arrays.toString(interests));//interest=Reading&interest=type the code&interest=study
        System.out.println(intro);

        return "ok";
    }


    @PostMapping(value = "/user/reg")
    public String register(
            //@RequestParam(value = "不能随便写，要和表单提交的参数的名字") name,value 都可以
            @RequestParam(value = "username") String username,  // username 不能随便写，最好是复制过来的，变量名随意
            //@RequestParam(value = "username2") String username2,  // 如果前端没有提供 username2 参数，则报错，400 错误 Required parameter 'username2' is not present.
            // 与  boolean required() default true; 属性有关， true 表示必须的（默认的，表示前端必须提供该参数信息），false 表示不是必须的(可以提供，也可以不提供)
            //@RequestParam(value = "password") String password,
            @RequestParam(value = "password") String a, // 变量名随意
            @RequestParam(name = "sex") Integer sex,
            @RequestParam(value = "interest") String[] interest,  // SpringMVC也可以自动帮助我们做类型转换，从前端提交的是'0'/
            // '1'字符串，可以自动转换成 Integer类型
            @RequestParam(value = "intro") String intro,
            //@RequestParam(value = "aqe" ,required = true) Integer age
            @RequestParam(value = "age" ,required = false,defaultValue ="20" ) Integer age // required=false
            // 前端没有提交该参数信息的话，那么该参数的值就是null
    ) {

        System.out.println(username);
        //System.out.println(password);
        System.out.println(a);
        System.out.println(sex);
        System.out.println(Arrays.toString(interest));
        System.out.println(intro);
        System.out.println(age);

        return "ok";

    }

}
