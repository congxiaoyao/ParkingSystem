package com.congxiaoyao.utils;

/**
 * 此类保存了各个类所用到的常量，出于某些原因，这些常量不能被存到JavaBean里
 * 所以专门在这里开辟空间以供存储
 *
 * Created by congxiaoyao on 2016/5/13.
 */
public class Constant {

    /**
     * 车位信息类所使用的关于车位状态标示的常量
     */
    public static class CarPort{
        //当前车位没车
        public static final int STATE_NOT_NULL = 1;
        //当前车位有车
        public static final int STATE_NULL = 0;
    }

    /**
     * 车辆进出记录类所用的常量
     */
    public static class ParkingRecord {
        //每小时停车费用
        public static final float PRICE = 2f;
    }

    /**
     * 成败结果类所使用的常量
     */
    public static class SFResult {
        //操作成功
        public static final int STATUS_SUCCESS = 1;
        //操作失败
        public static final int STATUS_FAILURE = 0;
    }
}