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
@WebServlet(name = "UserRegister", urlPatterns = "/webapi/register")
public class UserRegister extends MyHttpServlet {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String failPath = "../registerFail.jsp";

        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String tagid = request.getParameter("tagid");

        if (name == null || password == null || tagid == null) {
            request.setAttribute("reason", "参数错误");
            request.getRequestDispatcher(failPath).forward(request, response);
            return;
        }
        if (name.length() == 0 || password.length() == 0 || tagid.length() == 0) {
            request.setAttribute("reason", "参数错误");
            request.getRequestDispatcher(failPath).forward(request, response);
            return;
        }

        UserTable table = new UserTable();
        ArrayList<User> users = table.getItemByParamter("name", name);
        if (users == null || users.size() == 0) {
            users = table.getItemByParamter("tagid", tagid);
            if (users == null || users.size() == 0) {
                table.insert(new User(name, password, tagid));
                response.sendRedirect(request.getContextPath() + "/registerSuccess.jsp");
            }else {
                request.setAttribute("reason", "tagid已存在");
                request.getRequestDispatcher(failPath).forward(request, response);
            }
        }else {
            request.setAttribute("reason", "用户名存在");
            request.getRequestDispatcher(failPath).forward(request, response);
        }
        table.closeConnection();
    }
}
