package com.congxiaoyao.beans;

import com.congxiaoyao.utils.Constant;
import com.google.gson.annotations.Expose;
import com.my.dbprocessor.BaseBean;

/**
 * 封装了一个车位的数据信息
 * Created by congxiaoyao on 2016/3/22.
 */
public class Carport extends BaseBean{

    @Expose
    private int id;             //车位id
    @Expose
    private int state;          //车位状态

    public Carport(){
        super();
    }


    public Carport(int id, int state) {
        super();
        this.id = id;
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public void setNoCar() {
        this.state = Constant.CarPort.STATE_NULL;
    }

    public void setHasCar() {
        this.state = Constant.CarPort.STATE_NOT_NULL;
    }

    public boolean isCarportNull() {
        return state == Constant.CarPort.STATE_NULL;
    }

    public String getStateAndToString(){
        return state == Constant.CarPort.STATE_NULL ?
                "无车" : "有车";
    }

    @Override
    public String toString() {
        return "Carport{" +
                "id=" + id +
                ", state=" + state +
                '}';
    }
}