package com.congxiaoyao.service;

import com.congxiaoyao.beans.User;
import com.congxiaoyao.dao.UserTable;
import com.congxiaoyao.utils.GsonHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by congxiaoyao on 2016/5/14.
 */
@WebServlet(name = "QueryUserInfo", urlPatterns = "/webapi/userinfo")

public class QueryUserInfo extends MyHttpServlet {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String colName = null;
        String value = null;
        String name = request.getParameter("name");
        String tagid = request.getParameter("tagid");

        //检查参数
        if (name == null) {
            if (tagid == null) {
                User user = (User) request.getSession().getAttribute("user");
                if (user == null) {
                    printFailure("请提交name或tagid");
                    return;
                }else {
                    colName = "name";
                    value = user.getName();
                }
            } else {
                colName = "tagid";
                value = tagid;
            }
        }else {
            colName = "name";
            value = name;
        }

        UserTable table = new UserTable();
        if (value == null) {
            print(GsonHelper.toJson(table.getAllItem()));
        } else {
            ArrayList<User> users = table.getItemByParamter(colName, value);
            if (users == null || users.size() == 0) {
                printFailure("查无此人");
                return;
            }
            if (users.size() != 1) {
                printFailure("服务器错误");
                return;
            }
            print(GsonHelper.toJson(users.get(0)));
        }
        table.closeConnection();
    }
}
