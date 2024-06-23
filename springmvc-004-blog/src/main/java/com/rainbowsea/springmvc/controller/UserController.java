package com.rainbowsea.springmvc.controller;


import com.rainbowsea.springmvc.pojo.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;

@Controller  // 交给 Spring IOC 容器管理
public class UserController {
    // 首页
    @RequestMapping("/")
    public String toRegisterPage() {
        return "register";
    }


    @GetMapping("/user/reg")
    public String register(User user,
                           @RequestHeader(value = "Referer", required = false, defaultValue = "") String referer,
                           @RequestHeader(value = "Host", required = false, defaultValue = "") String host,
                           //@CookieValue(value = "cookie的名字") String id
                           @CookieValue(value = "id", required = false, defaultValue = "") String id
    ) {

        System.out.println(user);
        System.out.println(referer); // Referer: http://localhost:8080/springmvc/
        System.out.println(host);  // Host: localhost:8080
        System.out.println("客户端提交过来的 cookie ，它的值是 " + id);
        return "ok";

    }


    @PostMapping("/user/reg")
    public String register(User user,
                           @RequestHeader(value = "Referer", required = false, defaultValue = "") String referer,
                           @RequestHeader(value = "Host", required = false, defaultValue = "") String host) {

        System.out.println(user);
        System.out.println("referer: " + referer); // Referer: http://localhost:8080/springmvc/
        System.out.println("host: " + host);  // Host: localhost:8080
        return "ok";

    }




    //@PostMapping(value = "/user/reg")
    public String register(User user) {
        System.out.println(user);
        return "ok";
    }


    //@PostMapping(value = "/register")
    public String register(String username, String password, String sex, String[] hobby, String intro) {
        System.out.println(username + "," + password + "," + sex + "," + Arrays.toString(hobby) + "," + intro);
        return "ok";
    }


    //@PostMapping(value = "/user/reg")
    public String register(
            //@RequestParam(value = "不能随便写，要和表单提交的参数的名字") name,value 都可以
            @RequestParam(value = "username") String username,  // username 不能随便写，最好是复制过来的，变量名随意
            @RequestParam(value = "password") String a, // 变量名随意
            @RequestParam(name = "sex") Integer sex,
            @RequestParam(value = "interest") String[] interest,  // SpringMVC也可以自动帮助我们做类型转换，从前端提交的是'0'/
            // '1'字符串，可以自动转换成 Integer类型
            @RequestParam(value = "intro") String intro,
            @RequestParam(value = "age", required = false) Integer age
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


    //@RequestMapping(value = "/user/reg", method = RequestMethod.POST)
    public String register(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
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

}
