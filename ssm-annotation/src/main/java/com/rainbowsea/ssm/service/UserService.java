package com.rainbowsea.ssm.service;

import com.rainbowsea.ssm.bean.User;
import org.springframework.stereotype.Controller;



public interface UserService {

    /**
     * 根据用户id获取用户信息
     */

    User getById(Long id);
}
