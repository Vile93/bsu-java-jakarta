package com.example.bsu.controller;

import com.example.bsu.model.MathData;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

@WebServlet("/math/*")

public class MathController extends HttpServlet {
    private void doCompute(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        MathData mathData = mapper.readValue(req.getReader(), MathData.class);
        MathResponse mathResponse = new MathResponse(mathData.getNumber());
        double number = mathResponse.getTwice();

        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");
        PrintWriter out = res.getWriter();
        String jsonResponse = String.format("{ number: %f }", number);
        out.print(jsonResponse);
        out.flush();
    }
    public static  final Logger logger = Logger.getLogger(MathController.class.getName());

    @Override
    protected  void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String path = req.getPathInfo();
        logger.info(path);
        System.out.println(path);
       if("/twice".equals(path)) {
           doCompute(req,res);
       } else {
           res.sendError(HttpServletResponse.SC_BAD_GATEWAY);
       }
    }
}
