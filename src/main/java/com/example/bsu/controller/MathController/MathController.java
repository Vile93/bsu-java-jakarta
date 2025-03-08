package com.example.bsu.controller.MathController;

import com.example.bsu.service.MathService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/math/*")
public class MathController extends HttpServlet {
    private void doCompute(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        MathRequest mathRequest = mapper.readValue(req.getReader(), MathRequest.class);
        MathService mathService = new MathService();
        double number = mathService.getTwice(mathRequest.getNumber());
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");
        PrintWriter out = res.getWriter();
        String jsonResponse = String.format("{ \"number\": %f }", number);
        out.print(jsonResponse);
        out.flush();
    }

    protected  void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String path = req.getPathInfo();
       if("/twice".equals(path)) {
           doCompute(req,res);
       } else {
           res.sendError(HttpServletResponse.SC_NOT_FOUND);
       }
    }
}
