package com.congxiaoyao.service;

import com.congxiaoyao.beans.ParkingRecord;
import com.congxiaoyao.beans.ParkingRecord4Json;
import com.congxiaoyao.beans.User;
import com.congxiaoyao.dao.ParkingRecordTable;
import com.congxiaoyao.utils.GsonHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 通过tagid来查询
 * Created by congxiaoyao on 2016/5/13.
 */
@WebServlet(name = "QueryParkingRecord",urlPatterns = "/webapi/parkingrecord/query")
public class QueryParkingRecord extends MyHttpServlet {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String tagId = request.getParameter("tagid");
        if (tagId == null) {
            User user = (User) request.getSession().getAttribute("user");
            if (user == null) {
                System.out.println("no user no tagid");
                return;
            }
            tagId = user.getTagid();
        }
        System.out.println("QueryParkingRecord tagid = "+tagId);

        ParkingRecordTable table = new ParkingRecordTable();
        List<ParkingRecord> list = table.getLatestRecordByTagId(tagId);
        List<ParkingRecord4Json> jsonList = new ArrayList<>(list.size());
        list.stream().forEach(record -> jsonList.add(new ParkingRecord4Json(record)));
        print(GsonHelper.toJson(jsonList));
        table.closeConnection();
    }
}
