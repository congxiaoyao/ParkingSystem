package com.congxiaoyao.dao;

import com.congxiaoyao.beans.User;

/**
 * Created by congxiaoyao on 2016/5/13.
 */
public class UserTable extends DBProcessorPro<User> {

    @Override
    protected String getTableName() {
        return "table_user";
    }

    @Override
    protected User getEmptyBean() {
        return new User();
    }

}
