package com.congxiaoyao.listener;

import com.congxiaoyao.beans.Carport;
import com.congxiaoyao.dao.CarPortTable;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Created by congxiaoyao on 2016/5/13.
 */

@WebListener
public class MyServletContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        //检查table_carport表中的记录是否为6条
        CarPortTable carPortTable = new CarPortTable();
        int itemCount = carPortTable.getItemCount();
        if (itemCount != 6) {
            if (itemCount != 0) {
                carPortTable.deleteAll();
                System.err.println("车位数据异常");
            }
            for (int i = 0; i < 6; i++) {
                carPortTable.insert(new Carport(i, 0));
            }
        }
        carPortTable.closeConnection();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
