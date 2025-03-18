package com.example.bsu.service;

import com.example.bsu.controller.UserController.UserRequestUpdate;
import com.example.bsu.dao.UserDao;
import com.example.bsu.model.User;
import com.example.bsu.utils.ValidationFailedExceptionUtil;


public class UserService {
    public static User findById(int userId) {
        return  UserDao.findById(userId);
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
    public static void delete(int userId) {
        User user = UserDao.findById(userId);
        SessionService.deleteAll(user.getId());
        TodoService.deleteAll(user.getId());
        UserDao.delete(user);
    }
    public static void update(int userId, UserRequestUpdate userRequestUpdate) throws ValidationFailedExceptionUtil {
        User user = UserDao.findById(userId);
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
