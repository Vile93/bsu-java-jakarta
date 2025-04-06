package com.example.bsu.filter;

import jakarta.servlet.Filter;
import jakarta.annotation.Priority;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;

import java.io.IOException;

@Priority(2)
@WebFilter("/*")
public class ErrorHandlerFilter implements Filter {
    private static final Logger logger = LogManager.getLogger(ErrorHandlerFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            chain.doFilter(request, response);
        } catch (Exception e) {
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            e.printStackTrace();
            logger.error(e.toString());
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("message", e.getMessage());
            httpResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            httpResponse.setContentType("application/json");
            httpResponse.getWriter().write(jsonObject.toJSONString());
            httpResponse.getWriter().flush();
        }
    }
}
