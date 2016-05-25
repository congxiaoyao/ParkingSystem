package com.congxiaoyao.utils;

import com.google.gson.annotations.Expose;

/**
 * S指success F指fault 这个类的意义在于他的toString方法，是一个代表成功或失败的json字符串
 * 当使用者向一个网址提交一些数据的时候，服务器会向他返回成功或失败的状态，此类用于生成相关信息
 * Created by congxiaoyao on 2016/5/14.
 */
public class SFResult {

    private static final SFResult successResult = success("");

    @Expose
    private int status;
    @Expose
    private String reason;

    private SFResult(int status, String reason) {
        this.status = status;
        this.reason = reason;
    }

    public static SFResult success() {
        return successResult;
    }

    public static SFResult success(String reason) {
        return new SFResult(Constant.SFResult.STATUS_SUCCESS, reason);
    }

    public static SFResult failure(String reason) {
        return new SFResult(Constant.SFResult.STATUS_FAILURE, reason);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return GsonHelper.toJson(this);
    }
}
