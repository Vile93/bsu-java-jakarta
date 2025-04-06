package com.example.bsu.controller.MathController;

import com.example.bsu.service.MathService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;

import java.io.IOException;

@WebServlet("/api/math/*")
public class MathController extends HttpServlet {
    private void doCompute(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        MathRequest mathRequest = mapper.readValue(req.getReader(), MathRequest.class);
        double number = MathService.getTwice(mathRequest.getNumber());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("number", number);
        res.setContentType("application/json");
        res.setStatus(HttpServletResponse.SC_OK);
        res.getWriter().write(jsonObject.toJSONString());
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String path = req.getPathInfo();
       if("/twice".equals(path)) {
           doCompute(req,res);
       } else {
           res.setStatus(HttpServletResponse.SC_NOT_FOUND);
       }
    }
}
