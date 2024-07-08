package com.rainbowsea.springmvc.controller;



import com.rainbowsea.springmvc.pojo.User;
import com.rainbowsea.springmvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {

    @Autowired  // Spring 自动赋值，管理
    private UserService userService;

    @RequestMapping(value = "/user/{id}",method = RequestMethod.GET)
    public ResponseEntity<User> getById(@PathVariable("id") Long id) {
        User user = userService.getById(id);
        if(user == null) {
            // HttpStatus.NOT_FOUND  找不到错误  HTTP ERROR 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {
            return ResponseEntity.ok(user);
        }

    }

}
