package com.congxiaoyao.dao;

import com.congxiaoyao.beans.Carport;

/**
 * Created by congxiaoyao on 2016/5/13.
 */
public class CarPortTable extends DBProcessorPro<Carport> {

    @Override
    protected String getTableName() {
        return "table_carport";
    }

    @Override
    protected Carport getEmptyBean() {
        return new Carport();
    }
}
