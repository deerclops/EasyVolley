package com.terrence.easyvolley.net.callback;

import com.terrence.easyvolley.net.entity.RopResult;

/**
 * Created by DarkSouls on 2017/11/20.
 */

public interface ISuccess {
    void onSuccess(RopResult result);
    Class getResponseClass();
}
