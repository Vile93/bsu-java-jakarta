package com.example.bsu.service;


import org.mindrot.jbcrypt.BCrypt;

public class BcryptService {
    public static String encrypt(String value) {
        String salt = BCrypt.gensalt();
        return BCrypt.hashpw(value, salt);
    }
    public static boolean verify(String value, String hashedPassword) {
        return BCrypt.checkpw(value, hashedPassword);
    }
}
