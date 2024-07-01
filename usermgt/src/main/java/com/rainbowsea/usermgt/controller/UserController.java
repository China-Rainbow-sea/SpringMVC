package com.rainbowsea.usermgt.controller;


import com.rainbowsea.usermgt.bean.User;
import com.rainbowsea.usermgt.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller  // 交给 Spring IOC 容器管理
public class UserController {

    @Autowired  // 自动赋值
    private UserDao userDao;  // Could not autowire. No beans of 'UserDao' type found.

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String list(Model model) {
        // 查询数据库，获取用户列表List集合
        List<User> users = userDao.selectAll();
        // 将用户列表存储到 request 域当中
        model.addAttribute("users", users);
        // 转发到视图
        return "user_list";
    }


    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public String save(User user) {
        // 调用 userDao保存用户信息
        userDao.insert(user);
        // 重定向到用户列表页面（重新让浏览器发送一次全新的请求，去请求列表页面）
        return "redirect:/user";
    }


    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public String detail(@PathVariable(value = "id") Long id, Model model) {
        // 通过 id 查找用户信息
        User user = userDao.selectById(id);
        // 将用户休息存储到 request 域当中
        model.addAttribute("user", user);

        // 转发到视图
        return "user_edit";
    }


    @RequestMapping(value = "/user", method = RequestMethod.PUT)
    public String modify(User user) {
        // 修改用户信息
        userDao.upate(user);
        // 重定向列表页面
        return "redirect:/user";
    }



    @RequestMapping(value = "/user/{id}",method = RequestMethod.DELETE)
    public String del(@PathVariable(value = "id") Long id) {
        // 调用 dao删除用户
        userDao.deleteByid(id);

        // 重定向到列表
        return "redirect:/user";
    }

}
