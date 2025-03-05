package com.example.bsu.controller.AuthController;

import com.example.bsu.service.AuthService;
import com.example.bsu.utils.ValidationFailedExceptionUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.ValueInstantiationException;
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
            AuthService.login(response,authRequestLogin);
    }
    private void doLogout(HttpServletRequest request, HttpServletResponse response) throws IOException {
            AuthService.logout(request,response);
    }
    private void doRegister(HttpServletRequest request, HttpServletResponse response) throws IOException, ValidationFailedExceptionUtil {
            try {
                ObjectMapper mapper = new ObjectMapper();
                AuthRequestRegister authRequestRegister = mapper.readValue(request.getReader(), AuthRequestRegister.class);
                AuthService.register(response, authRequestRegister);
            } catch (ValidationFailedExceptionUtil e) {
                throw new ValidationFailedExceptionUtil("test");
            }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
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
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write("{\"message\":\"Not Found\"}");
            }
        } catch (ValidationFailedExceptionUtil e) {

        }
    }
}
