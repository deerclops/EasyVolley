package com.terrence.easyvolley.sample.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.terrence.easyvolley.net.entity.RopResult;

/**
 * @author Lu Ping
 *         created at 2016/9/23 0023 9:21 with joy
 */
public class SessionRsp extends RopResult {

    @JSONField(name = "session")
    public String value;
    @JSONField(name = "recipient")
    public UserInfo userInfo;

    public SessionRsp() {
    }

    @Override
    public String toString() {
        return "session: " + value + ", recipient: " + (userInfo==null?"null":userInfo.toString());
    }

    public static class UserInfo {
        public String name;
        public String phone;

        public UserInfo() {
        }

        @Override
        public String toString() {
            return "name: " + name + ", phone: " + phone;
        }
    }
}
