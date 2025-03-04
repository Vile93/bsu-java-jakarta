package com.example.bsu.service;

import com.example.bsu.controller.UserController.UserRequestUpdate;
import com.example.bsu.dao.UserDao;
import com.example.bsu.model.User;

public class UserService {
    public static User findById(int id) {
        return  UserDao.findById(id);
    }
    public static User findByUsername(String username) {
        return UserDao.findByUsername(username);
    }
    public static void create(User user) {
        UserDao.save(user);
    }
    public static void delete(int id) {
        User user = UserDao.findById(id);
        SessionService.deleteAll(user.getId());
        TodoService.deleteAll(user.getId());
        UserDao.delete(user);
    }
    public static void update(int id, UserRequestUpdate userRequestUpdate) {
        User user = UserDao.findById(id);
        if(userRequestUpdate.getEmail() != null) {
            user.setEmail(userRequestUpdate.getEmail());
        }
        if(userRequestUpdate.getUsername() != null) {
            user.setName(userRequestUpdate.getUsername());
        }
        UserDao.update(user);
    }
}
