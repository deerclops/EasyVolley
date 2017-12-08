package com.terrence.easyvolley.sample.entity;

import com.terrence.easyvolley.annotation.Encrypt;

/**
 * Created by DarkSouls on 2017/12/24.
 */

public class LoginParam {
    public String account;
    public boolean force;
    public int userType;
    public long deviceNum;
    public int deviceType;
    @Encrypt(value = false)
    public String password;

    public LoginParam() {
        force = true;
        userType = 2;
        deviceNum = 3588170056L;
        deviceType = 4;
    }
}
