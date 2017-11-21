package com.terrence.easyvolley.net;

import com.alibaba.fastjson.JSON;

import java.util.List;

/**
 * Created by DarkSouls on 2017/11/21.
 */

public class JsonParseHelper {

    public static <T> T parseJsonObject(String json, Class<T> clazz){
        return JSON.parseObject(json,clazz);
    }

    public static <T> List<T> parseJsonArray(String json, Class<T> clazz){
        return JSON.parseArray(json, clazz);
    }
}
