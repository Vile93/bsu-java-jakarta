package com.example.bsu.service;

import com.example.bsu.controller.UserController.UserRequestUpdate;
import com.example.bsu.dao.UserDao;
import com.example.bsu.model.User;
import com.example.bsu.utils.ValidationFailedExceptionUtil;

import java.util.logging.Logger;


public class UserService {
    public static User findById(int id) {
        return  UserDao.findById(id);
    }
    public static User findByUsername(String username) {
        return UserDao.findByUsername(username);
    }
    public static void create(User user) throws ValidationFailedExceptionUtil {
        ValidationFailedExceptionUtil ve = new ValidationFailedExceptionUtil();
        ve.validate(user);
        String password = BcryptService.encrypt(user.getPassword());
        user.setPassword(password);
        UserDao.save(user);
    }
    public static void delete(int id) {
        User user = UserDao.findById(id);
        SessionService.deleteAll(user.getId());
        TodoService.deleteAll(user.getId());
        UserDao.delete(user);
    }
    public static void update(int id, UserRequestUpdate userRequestUpdate) throws ValidationFailedExceptionUtil {
        User user = UserDao.findById(id);
        if(userRequestUpdate.getUsername() != null) {
            user.setName(userRequestUpdate.getUsername());
        }
        if(userRequestUpdate.getEmail() != null) {
            user.setEmail(userRequestUpdate.getEmail());
            user.setVerified(false);
        }
        ValidationFailedExceptionUtil ve = new ValidationFailedExceptionUtil();
        ve.validate(user);
        UserDao.update(user);
    }
    public static void update(User user) throws ValidationFailedExceptionUtil {
        ValidationFailedExceptionUtil ve = new ValidationFailedExceptionUtil();
        ve.validate(user);
        UserDao.update(user);
    }
}
