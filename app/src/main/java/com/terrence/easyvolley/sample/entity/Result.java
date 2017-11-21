package com.terrence.easyvolley.sample.entity;

import java.util.List;

/**
 * Created by DarkSouls on 2017/11/21.
 */

public class Result {

    public boolean error;
    public List<ResultItem> results;

    public Result() {
    }

    @Override
    public String toString() {
        StringBuilder sb=new StringBuilder();
        sb.append(error).append(":\n");
        for (ResultItem item : results) {
            if(item!=null)
                sb.append(item.toString()).append("\n");
        }
        return sb.toString();
    }
}
