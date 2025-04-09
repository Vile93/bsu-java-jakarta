package com.example.bsu.filter;

import jakarta.annotation.Priority;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

@Priority(4)
@WebFilter("/*")
public class PageProxyFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String contextPath = req.getContextPath();
        String servletPath = req.getServletPath();
        if((contextPath + servletPath).contains("/api") || (contextPath + servletPath).contains("index") || (contextPath + servletPath).contains("images")) {
            chain.doFilter(request, response);
        } else {
            if(!response.isCommitted()) {
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }
        }
    }
}
