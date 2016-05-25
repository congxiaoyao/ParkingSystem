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
@WebServlet(name = "Recharge",urlPatterns = "/webapi/recharge")
public class Recharge extends MyHttpServlet {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //检查密码
        String password = request.getParameter("password");
        if (!"5677678".equals(password)) {
            printFailure("密码错误");
            return;
        }
        //检查金额
        int money = 0;
        try {
            money = Integer.parseInt(request.getParameter("money"));
            if (money < 0) {
                printFailure("金额小于0");
                return;
            }
        } catch (NumberFormatException e) {
            printFailure("金额错误");
            return;
        }

        //检查要查询的参数
        String colName = null;
        String value = null;
        String name = request.getParameter("name");
        String tagId = request.getParameter("tagid");
        if (name == null) {
            if (tagId == null) {
                printFailure("参数错误");
                return;
            } else {
                colName = "tagid";
                value = tagId;
            }
        }else {
            colName = "name";
            value = name;
        }

        UserTable userTable = new UserTable();
        System.out.println("recharge-->");
        System.out.println(colName);
        System.out.println(value);
        ArrayList<User> users = userTable.getItemByParamter(colName, value);

        if (users == null || users.size() == 0) {
            printFailure("找不到用户");
            userTable.closeConnection();
            return;
        }

        if (users.size() != 1) {
            printFailure("数据库错误");
            userTable.closeConnection();
            return;
        }

        User user = users.get(0);
        user.rechargeMoney(money);

        userTable.update(user);
        userTable.closeConnection();

        printSuccess();
    }
}
