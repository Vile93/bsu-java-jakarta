package com.example.bsu.controller.AuthController;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/api/auth/*")
public class AuthController extends HttpServlet {
    private void doLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        AuthRequestLogin authRequestLogin = mapper.readValue(request.getReader(), AuthRequestLogin.class);

    }
    private void doLogout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    private void doRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        System.out.println(path);
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
