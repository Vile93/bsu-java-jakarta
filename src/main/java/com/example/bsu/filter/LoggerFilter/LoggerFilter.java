package com.example.bsu.filter.LoggerFilter;


import com.example.bsu.controller.MathController.MathController;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import jakarta.annotation.Priority;

import java.io.IOException;

@Priority(2)
@WebFilter("/*")
public class LoggerFilter implements Filter {
    private static  final Logger logger = LogManager.getLogger(MathController.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String path = req.getPathInfo();
        logger.info(path);
        chain.doFilter(request, response);
    }
}
