package com.example.bsu.controller.AuthController;

import com.example.bsu.service.AuthService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/api/auth/*")
public class AuthController extends HttpServlet {
    private void doLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
            ObjectMapper mapper = new ObjectMapper();
            AuthRequestLogin authRequestLogin = mapper.readValue(request.getReader(), AuthRequestLogin.class);
            AuthService.login(request,response,authRequestLogin);
    }
    private void doLogout(HttpServletRequest request, HttpServletResponse response) {
            AuthService.logout(request,response);
    }
    private void doRegister(HttpServletRequest request, HttpServletResponse response) throws IOException {
            ObjectMapper mapper = new ObjectMapper();
            AuthRequestRegister authRequestRegister = mapper.readValue(request.getReader(), AuthRequestRegister.class);
            AuthService.register(request,response,authRequestRegister);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String path = request.getPathInfo();
        switch (path) {
            case "/login":
                doLogin(request, response);
                break;
            case "/logout":
                doLogout(request, response);
                break;
            case "/register":
                 doRegister(request, response);
                 break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
    }
}
