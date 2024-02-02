package uz.pdp.springFrameworkCore.filters;


import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
/*
public class SecurityFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String authentication = request.getHeader("Authorization");
        if (authentication != null) {
            filterChain.doFilter(request, response);
        }else {
            response.sendError(401,"Unauthorized, In order to proceed -> please add Authorization Header");
        }
    }
}*/
