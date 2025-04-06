package com.example.bsu.filter;


import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import jakarta.annotation.Priority;

import java.io.IOException;

@Priority(3)
@WebFilter("/*")
public class LoggerPathFilter implements Filter {
    private static  final Logger logger = LogManager.getLogger(LoggerPathFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String servletPath = req.getServletPath();
        logger.info(servletPath);
        chain.doFilter(request, response);
    }
}
