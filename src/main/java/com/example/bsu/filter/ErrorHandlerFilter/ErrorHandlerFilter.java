package com.example.bsu.filter.ErrorHandlerFilter;

import com.example.bsu.filter.LoggerFilter.LoggerFilter;
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
            logger.error(e);
            e.printStackTrace();
            logger.error(e.toString());
            String jsonResponse = "{ \"error\" : " + e.getMessage() + "}";
            httpResponse.setCharacterEncoding("UTF-8");
            httpResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            httpResponse.setContentType("application/json");
            httpResponse.getWriter().write(jsonResponse);
            httpResponse.getWriter().flush();
        }
    }
}
