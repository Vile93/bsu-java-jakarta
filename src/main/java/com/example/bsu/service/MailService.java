package com.example.bsu.service;

import com.example.bsu.dao.MailDao;
import com.example.bsu.model.Mail;
import com.example.bsu.model.Session;
import com.example.bsu.model.User;
import com.example.bsu.utils.MailUtil;
import com.example.bsu.utils.ValidationFailedExceptionUtil;

import java.util.UUID;
import java.util.logging.Logger;

public class MailService {
    private static final Logger logger = Logger.getLogger(MailService.class.getName());

    public static void sendCode(Session session) throws ValidationFailedExceptionUtil {

        if(session.getUser().isVerified() == true) {
            ValidationFailedExceptionUtil ve = new ValidationFailedExceptionUtil("This user already was verified");
            throw ve;
        }
        Mail mail = MailDao.findByUserId(session.getUser().getId());
        if(mail != null) {
            UUID mailId = UUID.randomUUID();
            mail.setVerificationCode(mailId);
            MailDao.update(mail);
            MailUtil.send(session.getUser().getEmail(),"Email verification","Your code: " + mailId + ". This code will expire in 15 minutes.");
            return;
        }
        Mail newMail = new Mail();
        UUID mailId = UUID.randomUUID();
        newMail.setVerificationCode(mailId);
        newMail.setUser(session.getUser());
        MailDao.save(newMail);
        MailUtil.send(session.getUser().getEmail(),"Email verification","Your code: " + mailId + " . This code will expire in 15 minutes.");
    }
    public static void check(UUID mailId,Session session) throws ValidationFailedExceptionUtil {
        if(session.getUser().isVerified() == true) {
            ValidationFailedExceptionUtil ve = new ValidationFailedExceptionUtil("This user already verified");
            throw ve;
        }
        Mail mail = MailDao.findByUserId(session.getUser().getId());
        if(!mail.getVerificationCode().toString().equals(mailId.toString())) {
            ValidationFailedExceptionUtil ve = new ValidationFailedExceptionUtil("Incrrect verification code");
            throw ve;
        } else {
            User user = mail.getUser();
            user.setVerified(true);
            UserService.update(user);
        }

    }
}
