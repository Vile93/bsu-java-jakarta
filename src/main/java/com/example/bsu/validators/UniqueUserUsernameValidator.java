package com.example.bsu.validators;

import com.example.bsu.annotations.UniqueUserUsername;
import com.example.bsu.dao.UserDao;
import com.example.bsu.model.User;

import javax.validation.ConstraintValidator;

public class UniqueUserUsernameValidator implements ConstraintValidator<UniqueUserUsername, String> {
    public UniqueUserUsernameValidator() {}
    public boolean isValid(String username) {
        User user = UserDao.findByUsername(username);
        return user == null;
    }
}
