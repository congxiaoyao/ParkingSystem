package com.congxiaoyao.beans;

import com.congxiaoyao.utils.Constant;
import com.google.gson.annotations.Expose;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

/**
 * 此类通过ParkingRecord类的数据计算而来 是为了给用户展示数据而构建的类
 * Created by congxiaoyao on 2016/5/13.
 */
public class ParkingRecord4Json {

    public static final Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
    public static final SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm");

    @Expose
    private float money;
    @Expose
    private String enterTimeStr;
    @Expose
    private String leaveTimeStr;
    @Expose
    private String stayTime;

    public ParkingRecord4Json(ParkingRecord parkingRecord) {
        long enter = parkingRecord.getEnter();
        long leave = parkingRecord.getLeave();
        int mills = (int) Math.abs(enter - leave);
        money = mills / (float) (60 * 60 * 1000) * Constant.ParkingRecord.PRICE;
        money = (int)(money*100)/100.0f;
        calendar.setTimeInMillis(enter);
        enterTimeStr = dateFormat.format(calendar.getTime());
        calendar.setTimeInMillis(leave);
        leaveTimeStr = dateFormat.format(calendar.getTime());
        calendar.setTimeInMillis(mills);
        if (mills >= 24 * 60 * 60 * 1000) {
            stayTime = "大于一天";
        }else {
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            int minute = calendar.get(Calendar.MINUTE);
            int second = calendar.get(Calendar.SECOND);
            stayTime = hour + "小时" + minute + "分钟" + second + "秒";
        }
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    public String getEnterTimeStr() {
        return enterTimeStr;
    }

    public void setEnterTimeStr(String enterTimeStr) {
        this.enterTimeStr = enterTimeStr;
    }

    public String getLeaveTimeStr() {
        return leaveTimeStr;
    }

    public void setLeaveTimeStr(String leaveTimeStr) {
        this.leaveTimeStr = leaveTimeStr;
    }

    public String getStayTime() {
        return stayTime;
    }

    public void setStayTime(String stayTime) {
        this.stayTime = stayTime;
    }

    @Override
    public String toString() {
        return "ParkingRecord4Json{" +
                "money=" + money +
                ", enterTimeStr='" + enterTimeStr + '\'' +
                ", leaveTimeStr='" + leaveTimeStr + '\'' +
                ", stayTime='" + stayTime + '\'' +
                '}';
    }
}
