package com.congxiaoyao.service;

import com.congxiaoyao.beans.User;
import com.congxiaoyao.dao.UserTable;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by congxiaoyao on 2016/5/14.
 */
@WebServlet(name = "CheckLogin", urlPatterns = "/webapi/checklogin")

public class CheckLogin extends MyHttpServlet {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String password = request.getParameter("password");
        UserTable table = new UserTable();
        ArrayList<User> users = table.getItemByParamter("name", name);
        if (users.size() == 1 && users.get(0).getPassword().equals(password)) {
            request.getSession().setAttribute("user", users.get(0));
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        }else {
            printFailure("用户名密码错误");
            response.sendRedirect(request.getContextPath() + "/error.jsp");
        }
        table.closeConnection();
    }
}
