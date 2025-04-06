package com.example.bsu.controller.WatcherController;

import com.example.bsu.model.Session;
import com.example.bsu.model.UserTodo;
import com.example.bsu.service.WatcherService;
import com.example.bsu.utils.BadRequestExceptionUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.util.List;

@WebServlet("/api/watchers")
public class WatcherController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Session userSession = (Session) request.getAttribute("session");
            int todoId = Integer.parseInt(request.getParameter("todoId"));
            List<UserTodo> userTodos = WatcherService.findByTodoId(userSession.getUser(), todoId);
            JSONArray jsonArray = new JSONArray();
            for(int i = 0; i < userTodos.size(); i++) {
                JSONObject jsonObject = new JSONObject();
                UserTodo userTodo = userTodos.get(i);
                jsonObject.put("username", userTodo.getUser().getName());
                jsonArray.add(jsonObject);
            }
            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write(jsonArray.toJSONString());
        } catch (BadRequestExceptionUtil e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Session userSession = (Session) request.getAttribute("session");
            int todoId = Integer.parseInt(request.getParameter("todoId"));
            String username = request.getParameter("username");
            WatcherService.create(userSession.getUser(), username, todoId);
        } catch(BadRequestExceptionUtil e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        }
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Session userSession = (Session) request.getAttribute("session");
        String username = request.getParameter("username");
        int todoId = Integer.parseInt(request.getParameter("todoId"));
        WatcherService.deleteByUsername(userSession.getUser(), username, todoId);
    }
}
