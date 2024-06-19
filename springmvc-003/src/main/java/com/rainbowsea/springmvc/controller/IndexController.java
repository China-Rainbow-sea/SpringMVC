package com.rainbowsea.springmvc.controller;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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

    // 当前端发送的请求路径是：/user/login ，并且发送的请求方式是以POST方式请求的，则可以正常映射
    // 当前端发送的请求路径不是：/user/login ，就算请求方式是以POST方式请求的，也不会映射到这个方法上
    // 当前端发送的请求路径是：/user/login ，请求方式是以get方式请求的，也不会映射到这个方法上
    @RequestMapping(value = "/login",method = {RequestMethod.POST})
    public String userLogin() {
        System.out.println("处理登陆的业务...");
        return "ok";
    }
    // Method 'GET' is not supported.








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



    //@RequestMapping(value = "/x?y/testAntValue")
    //@RequestMapping(value = "/x*y/testAntValue")
    // @RequestMapping(value = "/**/testAntValue") // 报错了，Spring6报错，Spring5不会报错
    @RequestMapping(value = "/testAntValue/**") // 报错了，Spring6报错，Spring5不会报错
    public String testRequestMappingAntValue() {
        return "ok";
    }



    //@RequestMapping(value = "/login/admin/123")
/*    @RequestMapping(value = "/login/{username}/{password}")
    public String testRESTFulURL(@PathVariable("username") String username, @PathVariable("password") String password) {
        System.out.println("用户名： " + username + " 密码: " + password);
        return "ok";
    }

    */


    @RequestMapping(value = "/login/{a}/{b}")
    public String testRESTFulURL(@PathVariable("a") String username, @PathVariable("b") String password) {
        System.out.println("用户名： " + username + " 密码: " + password);
        return "ok";
    }





    @PostMapping(value = "/user/login")
    //@GetMapping(value = "/user/login")
/*    public String userLogin() {
        System.out.println("处理登陆的业务...");
        return "ok";
    }*/


    // 当RequestMapping 注解当中条件了 params ，则表示添加了新的约束条件
    // 当请求路径是 /testParams，并且提交的参数包括 username 和 password 时，才能映射成功
    @RequestMapping(value = "/testParams", params = {"username","password"})
    //@RequestMapping(value = "/testParams", params = {"username=zhangsan","password"})
    //@RequestMapping(value = "/testParams", params = {"username!=zhangsan","password"})
    //@RequestMapping(value = "/testParams", params = {"!username","password"})

    public String testParam() {
        return "ok";
        // Invalid request parameters.
    }


    // 只有当请求路径是：/testHeaders，并且请求当中有 Referer 和 Host ，这样才能映射成功
    //@RequestMapping(value = "/testHeaders",headers = {"Referer","Host"})
    //@RequestMapping(value = "/testHeaders",headers = {"Referer=http://localhost:8080/springmvc/","Host"})
    //@RequestMapping(value = "/testHeaders",headers = {"Referer!=http://localhost:8080/springmvc/","Host"})
    @RequestMapping(value = "/testHeaders",headers = {"Referer=http://localhost:9090/springmvc/","Host"})
    public String testHeaders() {
        return "ok";

        // 404 No endpoint GET /springmvc/testHeaders. 找不到资源
    }
}
