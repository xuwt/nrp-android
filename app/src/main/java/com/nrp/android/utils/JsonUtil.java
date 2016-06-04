package com.nrp.android.utils;


import com.google.gson.Gson;


public class JsonUtil {

    //返回状态码
    public int code;

    /**
     * convert java object to json string
     *
     * @param object
     * @return
     */
    public static String getJson(Object object) {
        return new Gson().toJson(object);
    }

    /**
     * parse json string into java object
     *
     * @param json  json string
     * @param clazz class of target object
     * @param <T>
     * @return
     */
    public static <T> T getEntity(String json, Class<T> clazz) {
        return new Gson().fromJson(json, clazz);
    }


}
