package com.example.bsu.service;

import com.example.bsu.dao.SessionDao;
import com.example.bsu.model.Session;
import com.example.bsu.model.User;

import java.util.UUID;

public class SessionService {
    public static Session findById(UUID uuid) {
        return SessionDao.findById(uuid);
    }
    public static UUID create(User user) {
        UUID uuid = UUID.randomUUID();
        Session session = new Session(uuid, user);
        SessionDao.save(session);
        return uuid;
    }
    public static void delete(UUID uuid) {
        Session session = SessionDao.findById(uuid);
        SessionDao.delete(session);
    }
}
