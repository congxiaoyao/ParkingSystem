package com.congxiaoyao.dao;

import com.my.dbprocessor.BaseBean;
import com.my.dbprocessor.DBLinker;
import com.my.dbprocessor.DBProcessorAdapter;

import java.sql.SQLException;

/**
 * Created by congxiaoyao on 2016/5/13.
 */
public abstract class DBProcessorPro<T extends BaseBean> extends DBProcessorAdapter<T> {

    public void deleteAll(){
        String sql = "DELETE FROM "+getTableName();
        try {
            statement = linker.getConnection().prepareStatement(sql);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void linkToDatabases(DBLinker linker) {
        DBLinkHelper.linkToMySql(linker);
    }

    public DBLinker getLinker() {
        return linker;
    }
}
