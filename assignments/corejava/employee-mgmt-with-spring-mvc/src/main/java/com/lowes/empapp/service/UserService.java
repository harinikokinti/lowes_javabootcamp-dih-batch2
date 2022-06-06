package com.lowes.empapp.service;

import com.lowes.empapp.dao.UserDao;
import com.lowes.empapp.model.User;
import org.springframework.beans.factory.annotation.Autowired;

public class UserService {
    @Autowired
    public UserDao userDao;

    // add user
    public void addUser(User user) {
        userDao.addUser(user);
    }

    // validate username & password
    public boolean validateLogin(User user) {
        System.out.println("in user service");
        return userDao.validateLogin(user);
    }
}
