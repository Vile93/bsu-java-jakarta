package com.example.bsu.service;

import com.example.bsu.dao.UserDao;
import com.example.bsu.model.User;

public class UserService {
    public static void create(User user) {
        String password = BcryptService.encrypt(user.getPassword());
        User newUser = new User();
        newUser.setPassword(password);
        newUser.setName(user.getName());
        newUser.setEmail(user.getEmail());
        UserDao.save(newUser);
    }
}
