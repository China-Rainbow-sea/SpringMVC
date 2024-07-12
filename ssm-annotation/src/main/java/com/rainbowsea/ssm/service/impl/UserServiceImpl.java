package com.rainbowsea.ssm.service.impl;

import com.rainbowsea.ssm.bean.User;
import com.rainbowsea.ssm.dao.UserDao;
import com.rainbowsea.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Override
    public User getById(Long id) {
        return userDao.selectById(id);
    }
}
