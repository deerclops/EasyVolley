package com.terrence.easyvolley.sample.entity;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by DarkSouls on 2017/11/21.
 */

public class ResultItem {

    @JSONField(name = "_id")
    public String id;
    public String desc;
    public String publishedAt;
    public String source;
    public String type;
    public String url;
    public boolean used;
    public String who;

    public ResultItem() {
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(id).append(":").append(url);
        return sb.toString();
    }
}
