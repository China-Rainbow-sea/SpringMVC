package com.rainbowsea.springmvc.controller;

import com.rainbowsea.springmvc.bean.User;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.net.URI;


@Controller  // 交给 Spring IOC 容器管理
public class RequestBodyController {

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@RequestBody String requestBodyStr) {
        // @RequestBody 将 将请求体转换成user对象。在方法上使用
        System.out.println(requestBodyStr);

        return "ok";

    }


/*    @RequestMapping(value = "/save2", method = RequestMethod.POST)
    public String saveUser(@RequestBody User user) {
        // @RequestBody 将 将请求体转换成user对象。在方法上使用
        System.out.println(user);
// 不是逻辑视图，是普通字符串，因为前端发送的请求是 AJAX 请求
        return "ok";

    }*/

    @RequestMapping(value = "/save2", method = RequestMethod.POST)
    public String saveUser(RequestEntity<User> requestEntity) {
       // 获取请求方法
        HttpMethod method = requestEntity.getMethod();
        System.out.println(method);

        // 获取请求URL
        URI url = requestEntity.getUrl();
        System.out.println(url);

        // 获取请求头
        HttpHeaders headers = requestEntity.getHeaders();
        System.out.println(headers);

        // 获取请求头中的内容类型
        MediaType contentType = headers.getContentType();
        System.out.println(contentType);

        // 获取请求体：
        User user = requestEntity.getBody();
        System.out.println(user);


        return "ok";

    }


}
