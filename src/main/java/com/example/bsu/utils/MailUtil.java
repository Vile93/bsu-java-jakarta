package com.example.bsu.utils;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.github.cdimascio.dotenv.Dotenv;

import java.util.Properties;

public class MailUtil {
    private static final Logger logger = LogManager.getLogger(MailUtil.class);
    private static final Dotenv dotenv = Dotenv.load();
    private static final String username = dotenv.get("MAIL_USERNAME");
    private static final String password = dotenv.get("MAIL_PASSWORD");
    private static final String host = dotenv.get("MAIL_HOST");
    private static final Message message = init();
    private static Message init() {
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.starttls.required", "true");
        props.put("mail.smtp.port", "587");
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
            }
        });
        return new MimeMessage(session);
    }

    public static void send(String to, String subject, String text) {
        try {
            message.setFrom(new InternetAddress(username));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(subject);
            message.setText(text);
            Transport.send(message);
        } catch (Exception e) {
            logger.error("Error in sending mail", e);
        }
    }
}
