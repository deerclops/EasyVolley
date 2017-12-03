package com.terrence.easyvolley.net.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.List;
import java.util.Map;

/**
 * Created by DarkSouls on 2017/11/21.
 */

public class JsonParseHelper {

    public static <T> T parseJsonObject(String json, Class<T> clazz) {
        return JSON.parseObject(json, clazz);
    }

    public static <T> List<T> parseJsonArray(String json, Class<T> clazz) {
        return JSON.parseArray(json, clazz);
    }

    public static <KEY, VALUE> Map<KEY, VALUE> parseJsonMap(String json) {
        return JSON.parseObject(json, new TypeReference<Map<KEY, VALUE>>() {
        });
    }
}
