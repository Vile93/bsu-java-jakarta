package com.example.bsu.AuthController;

import com.example.bsu.controller.AuthController.AuthController;
import com.example.bsu.dao.SessionDao;
import com.example.bsu.dao.UserDao;
import com.example.bsu.model.User;
import com.example.bsu.service.BcryptService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;

public class LoginUserTest extends Mockito {

    @Before
    public void setUp() {
        User user = UserDao.findByUsername("registeredUser");
        if(user != null) {
            SessionDao.deleteAll(user.getId());
            UserDao.delete(user);
        }
        User newUser = new User();
        newUser.setName("registeredUser");
        newUser.setEmail("registeredUser@example.com");
        String password = BcryptService.encrypt("password");
        newUser.setPassword(password);
        UserDao.save(newUser);
    }

    @Test
    public void test() throws Exception {
            HttpServletRequest req = mock(HttpServletRequest.class);
            HttpServletResponse resp = mock(HttpServletResponse.class);
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            JSONObject requestJSON = new JSONObject();

            requestJSON.put("username", "registeredUser");
            requestJSON.put("password", "password");

            when(resp.getWriter()).thenReturn(pw);
            when(req.getReader()).thenReturn(new BufferedReader(new StringReader(requestJSON.toString())));
            when(req.getPathInfo()).thenReturn("/login");
            new AuthController().doPost(req, resp);

            verify(resp).setStatus(HttpServletResponse.SC_OK);
    }

    @After
    public void tearDown() {
        User user = UserDao.findByUsername("registeredUser");
        if(user != null) {
            SessionDao.deleteAll(user.getId());
            UserDao.delete(user);
        }
    }
}
