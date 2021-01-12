package com.zgq.plainarticle.service;

import com.zgq.plainarticle.bean.User;
import com.zgq.plainarticle.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;
    /**
     * 根据名字查找用户
     */
    public User selectUserByName(String name) {

        return userDao.findUserByName(name);
    }

}
