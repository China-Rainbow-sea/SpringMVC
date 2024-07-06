package com.rainbowsea.springmvc.service;

import com.rainbowsea.springmvc.bean.User;
import org.springframework.stereotype.Service;


// 注意使用了注解，要用上组件扫描上
@Service
public class UserService {


    public User getById(Long id) {
        if(id == 1) {
            return new User(11L,"张三","123");
        }
        return null;
    }
}
