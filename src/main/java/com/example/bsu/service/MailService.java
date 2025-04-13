package com.example.bsu.service;

import com.example.bsu.dao.MailDao;
import com.example.bsu.dao.UserDao;
import com.example.bsu.model.Mail;
import com.example.bsu.model.Session;
import com.example.bsu.model.User;
import com.example.bsu.utils.MailUtil;
import com.example.bsu.utils.ValidationFailedExceptionUtil;

import java.time.LocalDateTime;
import java.util.UUID;

public class MailService {

    public static void sendCode(Session session) throws ValidationFailedExceptionUtil {

        if(session.getUser().isVerified()) {
            throw  new ValidationFailedExceptionUtil("This user already was verified");
        }
        Mail mail = MailDao.findByUserId(session.getUser().getId());
        if(mail != null) {
            Mail newMail = new Mail();
            UUID mailId = UUID.randomUUID();
            newMail.setVerificationCode(mailId);
            newMail.setUser(mail.getUser());
            newMail.setId(mail.getId());
            MailDao.update(newMail);
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
        if(session.getUser().isVerified()) {
            throw new ValidationFailedExceptionUtil("This user already verified");
        }
        Mail mail = MailDao.findByUserId(session.getUser().getId());
        if(!mail.getVerificationCode().toString().equals(mailId.toString())) {
            throw new ValidationFailedExceptionUtil("Incorrect verification code");
        }
        LocalDateTime expiration = LocalDateTime.parse(mail.getExpiration());
        LocalDateTime now = LocalDateTime.now();
        if(expiration.isBefore(now)) {
            throw new ValidationFailedExceptionUtil("This code is no longer valid.");
        }
        User user = mail.getUser();
        user.setVerified(true);
        UserDao.update(user);
    }
}
