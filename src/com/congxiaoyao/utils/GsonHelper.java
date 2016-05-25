package com.congxiaoyao.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by congxiaoyao on 2016/3/29.
 */
public class GsonHelper {

    private static Gson gson = null;

    static {
        gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .create();
    }


    /**
     * 将Carport或ParkingRecord的list传入即可生成相应的json字符串
     * @param object
     * @return
     */
    public static String toJson(Object object) {
        return gson.toJson(object);
    }

}