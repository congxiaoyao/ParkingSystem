package com.congxiaoyao.filter;

import com.congxiaoyao.beans.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by congxiaoyao on 2016/5/16.
 */
//@WebFilter(filterName = "LoginFilter",urlPatterns = {"../parkingLot/parting.html",
//        "/webapi/parkingrecord/query"})

public class LoginFilter implements Filter {

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        System.out.println(request.getRequestURI());
        HttpServletResponse response = (HttpServletResponse) resp;
        User user = (User) request.getSession().getAttribute("user");
//        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login/login.html");
//        }
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
