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
 * Created by congxiaoyao on 2016/5/13.
 */
@WebServlet(name = "SubmitCarport",urlPatterns = "/webapi/carport/submit")
public class SubmitCarport extends MyHttpServlet {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Carport carport = null;
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            int state = Integer.parseInt(request.getParameter("state"));
            carport = new Carport(id, state);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            printFailure("参数错误");
            return;
        }

        if (carport != null) {
            CarPortTable table = new CarPortTable();
            table.update(carport);
            table.closeConnection();
        }

        printSuccess();

        new Thread(()->{
            CarPortTable table = new CarPortTable();
            ArrayList<Carport> carports = table.getAllItem();
            String json = GsonHelper.toJson(carports);
            WebSocketTest.sendAll(json);
            table.closeConnection();
        }).start();
    }
}
