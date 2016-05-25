package com.congxiaoyao.service;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by congxiaoyao on 2016/5/22.
 */
@WebServlet(name = "Logout",urlPatterns = "/webapi/logout")
public class Logout extends MyHttpServlet {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getSession().removeAttribute("user");
        request.getRequestDispatcher("../index.jsp").forward(request, response);
    }
}
