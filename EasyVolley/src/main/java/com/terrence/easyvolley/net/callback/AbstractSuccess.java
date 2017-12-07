package com.terrence.easyvolley.net.callback;

import com.terrence.easyvolley.net.entity.RopResult;

/**
 * Created by DarkSouls on 2017/12/22.
 */

public abstract class AbstractSuccess<T extends RopResult> implements ISuccess {

    private Class<T> mClazz;

    public AbstractSuccess(Class<T> clazz) {
        this.mClazz = clazz;
    }

    @Override
    public Class getResponseClass() {
        return this.mClazz;
    }

    @Override
    public void onSuccess(RopResult result) {
        success((T) result);
    }

    public abstract void success(T result);
}
