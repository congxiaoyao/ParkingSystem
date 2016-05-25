package com.congxiaoyao.service;

import com.congxiaoyao.beans.ParkingRecord;
import com.congxiaoyao.beans.ParkingRecord4Json;
import com.congxiaoyao.beans.User;
import com.congxiaoyao.dao.ParkingRecordTable;
import com.congxiaoyao.dao.UserTable;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by congxiaoyao on 2016/5/13.
 */
@WebServlet(name = "SubmitParkingRecord",urlPatterns = "/webapi/parkingrecord/submit")
public class SubmitParkingRecord extends MyHttpServlet {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ParkingRecord record = null;

        //检查参数是否合法
        try {
            String id = request.getParameter("tagid");
            long enter = Long.parseLong(request.getParameter("enter"));
            long leave = Long.parseLong(request.getParameter("leave"));
            record = new ParkingRecord(id, enter, leave);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            printFailure("格式错误");
            return;
        }

        if (record.getEnter() > record.getLeave()) {
            printFailure("时间错误");
            return;
        }

        //再核对用户信息
        UserTable userTable = new UserTable();
        ArrayList<User> users = userTable.getItemByParamter("tagid", record.getTagid());
        if (users == null) {
            printFailure("古怪的错误");
            userTable.closeConnection();
            return;
        }
        if (users.size() == 0) {
            printFailure("卡片未注册");
            userTable.closeConnection();
            return;
        }
        if (users != null && users.size() != 1) {
            printFailure("数据库错误");
            userTable.closeConnection();
            return;
        }

        //修改用户余额并插入记录
        User user = users.get(0);
        ParkingRecord4Json record4Json = new ParkingRecord4Json(record);
        float money = record4Json.getMoney();

        user.costMoney(money);
        userTable.update(user);
        userTable.closeConnection();

        ParkingRecordTable table = new ParkingRecordTable();
        table.insert(record);
        table.closeConnection();

        printSuccess();
    }
}
