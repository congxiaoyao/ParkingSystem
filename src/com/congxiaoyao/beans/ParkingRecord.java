package com.congxiaoyao.beans;

import com.my.dbprocessor.BaseBean;

/**
 * Created by congxiaoyao on 2016/5/13.
 */
public class ParkingRecord extends BaseBean {

    private String tagid;
    private long enter;
    private long leave;

    public ParkingRecord() {
        super();
    }

    public ParkingRecord(String tagid, long enter, long leave) {
        super();
        this.tagid = tagid;
        this.enter = enter;
        this.leave = leave;
    }

    public String getTagid() {
        return tagid;
    }

    public void setTagid(String tagid) {
        this.tagid = tagid;
    }

    public long getEnter() {
        return enter;
    }

    public void setEnter(long enter) {
        this.enter = enter;
    }

    public long getLeave() {
        return leave;
    }

    public void setLeave(long leave) {
        this.leave = leave;
    }

    @Override
    public String toString() {
        return "ParkingRecord{" +
                "tagid=" + tagid +
                ", enter=" + enter +
                ", leave=" + leave +
                '}';
    }
}
