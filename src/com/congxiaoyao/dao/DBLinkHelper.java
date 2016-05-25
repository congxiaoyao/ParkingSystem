package com.congxiaoyao.dao;

import com.my.dbprocessor.DBLinker;

import java.io.*;
import java.util.Properties;

/**
 * Created by congxiaoyao on 2016/5/15.
 */
public class DBLinkHelper {

    public static String dbUrl;
    public static String userName;
    public static String password;

    static {
        Properties properties = new Properties();
        try {
            InputStream inputStream = DBLinkHelper.class
                    .getResourceAsStream("/dbConfig.properties");
            properties.load(inputStream);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        dbUrl = properties.getProperty("dbUrl");
        userName = properties.getProperty("userName");
        password = properties.getProperty("password");
    }

    public static void linkToMySql(DBLinker linker) {
        linker.linkDB_MY_SQL(dbUrl, userName, password);
    }
}
