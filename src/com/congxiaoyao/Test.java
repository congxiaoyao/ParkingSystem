package com.congxiaoyao;

import com.congxiaoyao.beans.ParkingRecord;
import com.congxiaoyao.beans.ParkingRecord4Json;
import com.congxiaoyao.dao.ParkingRecordTable;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by congxiaoyao on 2016/5/13.
 */
public class Test {
    
    public static void main0(String[] args) {

        ParkingRecordTable table = new ParkingRecordTable();
        long enter = System.currentTimeMillis();
//        table.insert(new ParkingRecord(1, enter, enter + 1000 * 60 * 75));

        System.out.println(new ParkingRecord("1", enter, enter + 1000 * 60 * 75));

        List<ParkingRecord> allItem = table.getLatestRecordByTagId("0");
        for (ParkingRecord record : allItem) {
            ParkingRecord4Json temp = new ParkingRecord4Json(record);
            System.out.println(temp);
        }

        table.closeConnection();

    }

    public static void main(String[] args) {
        long mills = 1 * 60 * 60 * 1000;
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.setTimeInMillis(mills);
        System.out.println(calendar.get(Calendar.HOUR_OF_DAY));

    }

}
