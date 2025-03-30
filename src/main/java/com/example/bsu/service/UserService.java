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
    public static void delete(User user) {
        MailDao.deleteAllByUser(user);
        SessionDao.deleteAll(user.getId());
        TodoDao.deleteAll(user.getId());
        UserDao.delete(user);
    }
    public static void update(User user, UserRequestUpdate userRequestUpdate) throws ValidationFailedExceptionUtil {
        if (userRequestUpdate.getUsername() != null) {
            User dbUser = UserDao.findByUsername(userRequestUpdate.getUsername());
            if (dbUser != null) {
                throw new ValidationFailedExceptionUtil("Username already exists");
            }
        }
        User updatedUser = new User();
        updatedUser.setId(user.getId());
        updatedUser.setEmail(user.getEmail());
        updatedUser.setVerified(user.isVerified());
        if(userRequestUpdate.getPassword() != null && userRequestUpdate.getPassword().length() > 0) {
            updatedUser.setPassword(userRequestUpdate.getPassword());
        } else {
            updatedUser.setPassword(user.getPassword());
        }
        if(userRequestUpdate.getUsername() != null && userRequestUpdate.getUsername().length() > 0) {
            updatedUser.setName(userRequestUpdate.getUsername());
        } else {
            updatedUser.setName(user.getName());
        }
        ValidationFailedExceptionUtil ve = new ValidationFailedExceptionUtil();
        ve.validate(user);
        if(userRequestUpdate.getPassword() != null && userRequestUpdate.getPassword().length() > 0) {
            String encryptedPassword = BcryptService.encrypt(userRequestUpdate.getPassword());
            updatedUser.setPassword(encryptedPassword);
        }
        UserDao.update(updatedUser);
    }
}
