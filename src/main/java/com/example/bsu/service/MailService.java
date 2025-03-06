package com.example.bsu.service;

import com.example.bsu.dao.MailDao;
import com.example.bsu.model.Mail;
import com.example.bsu.model.Session;
import com.example.bsu.model.User;
import com.example.bsu.utils.MailUtil;

import java.util.UUID;
import java.util.logging.Logger;

public class MailService {
    private static final Logger logger = Logger.getLogger(MailService.class.getName());

    public static void sendCode(Session session) {
        if(session.getUser().isVerified() == true) {
            logger.info("this user already was verified");
            return;
        }
        Mail mail = MailDao.findByUserId(session.getUser().getId());
        if(mail != null) {
            UUID mailId = UUID.randomUUID();
            mail.setId(mailId);
            MailDao.update(mail);
            MailUtil.send(session.getUser().getEmail(),"Email verification","Your code: " + mailId + " . This code will expire in 15 minutes.");
            logger.info("email verification sent");
            return;
        }
        Mail newMail = new Mail();
        UUID mailId = UUID.randomUUID();
        newMail.setId(mailId);
        newMail.setUser(session.getUser());
        MailDao.save(newMail);
        MailUtil.send(session.getUser().getEmail(),"Email verification","Your code: " + mailId + " . This code will expire in 15 minutes.");
        logger.info("email verification sent");
    }
    public static void check(UUID mailId,Session session) {
        if(session.getUser().isVerified() == true) {
            logger.info("this user already was verified");
            return;
        }
        Mail mail = MailDao.findByUserId(session.getUser().getId());
        if(mail.getId().toString() != mailId.toString()) {
            logger.info("Incorrect id" + mailId.toString() + "|" + mail.getId().toString());
        } else {
            logger.info("correct id");
            User user = mail.getUser();
            user.setVerified(true);
            UserService.update(user);
        }

    }
}
