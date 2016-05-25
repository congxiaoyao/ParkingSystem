package com.congxiaoyao.service;

import com.congxiaoyao.beans.Carport;
import com.congxiaoyao.dao.CarPortTable;
import com.congxiaoyao.utils.GsonHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 查询所有车位状态 目前不允许提交任何参数
 * Created by congxiaoyao on 2016/5/13.
 */
@WebServlet(name = "QueryCarPort", urlPatterns = "/webapi/carport/query")
public class QueryCarPort extends MyHttpServlet {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CarPortTable table = new CarPortTable();
        ArrayList<Carport> carports = table.getAllItem();
        String json = GsonHelper.toJson(carports);
        print(json);
        table.closeConnection();
    }
}
