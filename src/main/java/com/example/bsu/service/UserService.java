package com.example.bsu.service;

import com.example.bsu.controller.UserController.UserRequestUpdate;
import com.example.bsu.dao.MailDao;
import com.example.bsu.dao.SessionDao;
import com.example.bsu.dao.TodoDao;
import com.example.bsu.dao.UserDao;
import com.example.bsu.model.User;
import com.example.bsu.utils.ValidationFailedExceptionUtil;


public class UserService {
    public static User find(int userId) {
        return  UserDao.findById(userId);
    }
  /*  public static void create(User user) throws ValidationFailedExceptionUtil {
        ValidationFailedExceptionUtil ve = new ValidationFailedExceptionUtil();
        ve.validate(user);
        String password = BcryptService.encrypt(user.getPassword());
        user.setPassword(password);
        UserDao.save(user);
    }*/
    public static void delete(User user) {
        MailDao.deleteAllByUser(user);
        SessionDao.deleteAll(user.getId());
        TodoDao.deleteAll(user.getId());
        UserDao.delete(user);
    }
    public static void update(int userId, UserRequestUpdate userRequestUpdate) throws ValidationFailedExceptionUtil {
        User user = UserDao.findById(userId);
        ValidationFailedExceptionUtil ve = new ValidationFailedExceptionUtil();
        ve.validate(user);
        UserDao.update(user);
    }
}
