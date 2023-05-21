package com.example.authms.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

public class JwtFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain)
            throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;
        final String authHeader = request.getHeader("authorization");
        if ("OPTIONS".equals(request.getMethod())){
            response.setStatus(HttpServletResponse.SC_OK);
            filterChain.doFilter(request, response);
        }else {
            if (authHeader == null || !authHeader.startsWith("Bearer ")){
                throw  new ServletException("An exception occured");
            }
        }
        final String token = authHeader.substring(7);
        Claims claims = Jwts.parser().setSigningKey("secret").parseClaimsJwt(token).getBody();
        request.setAttribute("claims", claims);
        request.setAttribute("endpoint", servletRequest.getParameter("id"));
        filterChain.doFilter(request, response);
    }
}
