package com.example.bsu.controller.UserController;


import com.example.bsu.model.Session;
import com.example.bsu.model.User;
import com.example.bsu.service.UserService;
import com.example.bsu.utils.ValidationFailedExceptionUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/api/users")
public class UserController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Session userSession = (Session) request.getAttribute("session");
        JSONObject jsonObject = new JSONObject();
        User user = UserService.find(userSession.getUser().getId());
        jsonObject.put("id", user.getId());
        jsonObject.put("name", user.getName());
        jsonObject.put("email", user.getEmail());
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().print(jsonObject.toJSONString());
    }
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Session userSession = (Session) request.getAttribute("session");
            ObjectMapper mapper = new ObjectMapper();
            UserRequestUpdate userRequestUpdate = mapper.readValue(request.getReader(), UserRequestUpdate.class);
            UserService.update(userSession.getUser(), userRequestUpdate);
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (ValidationFailedExceptionUtil ve) {
            response.setStatus(HttpServletResponse.SC_UNPROCESSABLE_CONTENT);
            response.setContentType("application/json");
            response.getWriter().write(ve.getJSONMessage());
        }
    }
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Session userSession = (Session) request.getAttribute("session");
        UserService.delete(userSession.getUser());
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
