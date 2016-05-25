package com.congxiaoyao.dao;

import com.congxiaoyao.beans.ParkingRecord;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by congxiaoyao on 2016/5/13.
 */
public class ParkingRecordTable extends DBProcessorPro<ParkingRecord> {

    @Override
    protected String getTableName() {
        return "table_parkingrecord";
    }

    @Override
    protected ParkingRecord getEmptyBean() {
        return new ParkingRecord();
    }

    public List<ParkingRecord> getLatestRecordByTagId(String tagId) {
        String sql = "SELECT * FROM table_parkingrecord WHERE tagid = ? " +
                "ORDER BY enter DESC LIMIT 0,10;";
        List<ParkingRecord> parkingRecords = null;
        try {
            statement = linker.getConnection().prepareStatement(sql);
            statement.setString(1, tagId);
            parkingRecords = analysisResultSet(statement.executeQuery());
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return parkingRecords;
    }
}
