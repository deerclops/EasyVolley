package com.terrence.easyvolley.net;

import com.terrence.easyvolley.net.callback.IFailure;
import com.terrence.easyvolley.net.callback.IHandleServerError;
import com.terrence.easyvolley.net.callback.IRequest;
import com.terrence.easyvolley.net.callback.ISessionExpired;
import com.terrence.easyvolley.net.callback.ISuccess;
import com.terrence.easyvolley.net.callback.IToastError;

/**
 * Created by DarkSouls on 2017/12/2.
 */

public final class NetEngineBuilder {

    private String mUrl;
    private String mMethodName;
    private String mMethodVersion;

    private IRequest mRequest;
    private ISuccess mSuccess;
    private ISessionExpired mSessionExpired;
    private IToastError mToastError;
    private IHandleServerError mHandleServerError;
    private IFailure mFailure;

    NetEngineBuilder() {
    }

    public NetEngineBuilder url(String url) {
        this.mUrl = url;
        return this;
    }

    public NetEngineBuilder apiName(String apiName) {
        this.mMethodName = apiName;
        return this;
    }

    public NetEngineBuilder apiVersion(String apiVersion) {
        this.mMethodVersion = apiVersion;
        return this;
    }

    public NetEngineBuilder onRequest(IRequest request) {
        this.mRequest = request;
        return this;
    }

    public NetEngineBuilder onSuccess(ISuccess success) {
        this.mSuccess = success;
        return this;
    }

    public NetEngineBuilder onSessionExpired(ISessionExpired sessionExpired) {
        this.mSessionExpired = sessionExpired;
        return this;
    }

    public NetEngineBuilder onToastError(IToastError toastError) {
        this.mToastError = toastError;
        return this;
    }

    public NetEngineBuilder onHandleServerError(IHandleServerError handleServerError) {
        this.mHandleServerError = handleServerError;
        return this;
    }

    public NetEngineBuilder onFailure(IFailure failure) {
        this.mFailure = failure;
        return this;
    }

    public final NetEngine build() {
        return new NetEngine(mUrl, mMethodName, mMethodVersion, mRequest, mSuccess, mSessionExpired, mToastError, mHandleServerError, mFailure);
    }

}
