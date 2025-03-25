package com.example.bsu.validators;

import com.example.bsu.annotations.UniqueUserEmail;
import com.example.bsu.dao.UserDao;
import com.example.bsu.model.User;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUserEmailValidator implements ConstraintValidator<UniqueUserEmail, String> {

    public UniqueUserEmailValidator() {}
    public boolean isValid(String email, ConstraintValidatorContext context) {
        User user = UserDao.findByEmail(email);
        return user == null;
    }
}
