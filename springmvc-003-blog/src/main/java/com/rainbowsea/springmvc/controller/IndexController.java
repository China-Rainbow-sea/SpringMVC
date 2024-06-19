package com.rainbowsea.springmvc.controller;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller // 交给 Spring IOC 容器管理
public class IndexController {

    @RequestMapping(value = "/")  // 首页
    public String index() {
        return "index";
    }


    // 当前端发送的请求路径是：/login ，并且发送的请求方式是以POST方式请求的，则可以正常映射
    // 当前端发送的请求路径不是：/login ，就算请求方式是以POST方式请求的，也不会映射到这个方法上
    // 当前端发送的请求路径是：/login ，请求方式是以get方式请求的，也不会映射到这个方法上
    @RequestMapping(value = "/login",method = {RequestMethod.PUT})
    public String userLogin() {
        System.out.println("处理登陆的业务...");
        return "ok";
    }
    // Method 'GET' is not supported.





    @RequestMapping(value = "/login/{username}/{password}")
    public String testRESTFulURL(@PathVariable("username") String username, @PathVariable("password") String password) {
        System.out.println("用户名： " + username + " 密码: " + password);
        return "ok";
    }






    //@RequestMapping(value = "/x?z/testAntValue")
    //@RequestMapping(value = "/x*z/testAntValue")
    //@RequestMapping(value = "/**/testAntValue")
    @RequestMapping(value = "/testAntValue/**") //  ** 在末尾 Spring65都可以
    public String testRequestMappingAntValue() {
        return "ok";
    }






    // 对于注解来说，如果是一个数组，数组中只有一个元素，大括号是可以省略的
    //@RequestMapping({"testvalue1","testvalue1"})
    //@RequestMapping(value = {"testvalue1","testvalue2"})
    // path 和 value 是同一个值
    @RequestMapping(path = {"testvalue1","testvalue2"})
    public String testRequestMappingValue() {
        // 处理业务逻辑
        // 返回逻辑视图
        return "test_value";
    }






}
