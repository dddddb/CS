package com.ldb.filter;


import com.sun.deploy.net.HttpResponse;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@Component
@WebFilter(filterName = "my1Filter", urlPatterns = {"/addMessage/*"})

public class MyFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request1 = (HttpServletRequest) request;
        Cookie[] cookies = request1.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("username".equals(cookie.getName())) {
                    chain.doFilter(request, response);
                    return;
                } else {
//                    request1.getRequestDispatcher("login.html").forward(request,response);
                    HttpServletResponse res = (HttpServletResponse) response;
//                    res.sendRedirect("login.html");
                    return;
                }
            }
        } else {
//                request1.getRequestDispatcher("login.html").forward(request,response);

            HttpServletResponse res = (HttpServletResponse) response;
//            res.sendRedirect("login.html");
            return;
        }

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
