package com.example.bsu.filter;


import com.example.bsu.dao.SessionDao;
import com.example.bsu.model.Session;
import com.example.bsu.service.CookieService;
import jakarta.annotation.Priority;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

@Priority(4)
@WebFilter(urlPatterns = {"/api/users/*", "/api/todos/*", "/api/mail/*", "/api/watchers/*"} )
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        Cookie sessionCookie = CookieService.getCookie(req, "session");
        if(sessionCookie == null) {
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        Session dbSession = SessionDao.findById(UUID.fromString(sessionCookie.getValue()));
        if(dbSession == null) {
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        LocalDateTime expiration = LocalDateTime.parse(dbSession.getExpiration());
        if(expiration.isBefore(LocalDateTime.now())) {
            SessionDao.deleteById(dbSession.getId());
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        String servletPath = req.getServletPath();
        if(servletPath.contains("/api/mail")) {
            request.setAttribute("session", dbSession);
            chain.doFilter(request, response);
            return;
        }
        if(!dbSession.getUser().isVerified()) {
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        request.setAttribute("session", dbSession);
        chain.doFilter(request, response);
    }
}
