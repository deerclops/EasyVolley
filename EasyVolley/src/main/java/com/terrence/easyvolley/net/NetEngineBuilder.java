package com.terrence.easyvolley.net;

import android.support.annotation.NonNull;

import com.terrence.easyvolley.app.Rop;
import com.terrence.easyvolley.net.callback.IFailure;
import com.terrence.easyvolley.net.callback.IHandleServerError;
import com.terrence.easyvolley.net.callback.IRequest;
import com.terrence.easyvolley.net.callback.ISessionExpired;
import com.terrence.easyvolley.net.callback.ISuccess;
import com.terrence.easyvolley.net.callback.IToastError;
import com.terrence.easyvolley.net.util.EncryptUtil;
import com.terrence.easyvolley.net.util.ParamGenerator;

import java.util.HashMap;
import java.util.Map;

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
    private HashMap<String, IHandleServerError> mHandleServerError;
    private IFailure mFailure;

    // Params
    private Map<String, String> mHeadParams = new HashMap<>();
    private Map<String, String> mBodyParamsEncrypted = new HashMap<>();
    private Map<String, String> mBodyParamsNotEncrypted = new HashMap<>();

    NetEngineBuilder() {
    }

    public NetEngineBuilder addBodyParam(Object paramObj) {
        if (paramObj != null) {
            HashMap<String, String> encryptedMap = new HashMap<>();
            HashMap<String, String> notEncryptedMap = new HashMap<>();
            ParamGenerator.obj2Map(paramObj, encryptedMap, notEncryptedMap);
            mBodyParamsEncrypted.putAll(encryptedMap);
            mBodyParamsNotEncrypted.putAll(notEncryptedMap);
        }
        return this;
    }

    public NetEngineBuilder addBodyParam(String paramName, String paramValue) {
        addBodyParam(paramName, paramValue, true);
        return this;
    }

    public NetEngineBuilder addBodyParam(String paramName, String paramValue, boolean needEncrypted) {
        if (needEncrypted) {
            mBodyParamsEncrypted.put(paramName, paramValue);
        } else {
            mBodyParamsNotEncrypted.put(paramName, paramValue);
        }
        return this;
    }

    public NetEngineBuilder addHeadParam(String paramName, String paramValue) {
        mHeadParams.put(paramName, paramValue);
        return this;
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

    public NetEngineBuilder addOnHandleServerError(@NonNull String subError, IHandleServerError handleServerError) {
        if (mHandleServerError == null) {
            mHandleServerError = new HashMap<>();
        }
        mHandleServerError.put(subError, handleServerError);
        return this;
    }

    public NetEngineBuilder onFailure(IFailure failure) {
        this.mFailure = failure;
        return this;
    }

    public final NetEngine build() {
        encryptParams();
        mBodyParamsEncrypted.putAll(mBodyParamsNotEncrypted);
        return new NetEngine(mUrl, mMethodName, mMethodVersion,
                mRequest, mSuccess, mSessionExpired, mToastError, mHandleServerError, mFailure,
                mHeadParams, mBodyParamsEncrypted);
    }

    private void encryptParams() {
        mBodyParamsEncrypted.put("appcode", Rop.getAppCode());
        mBodyParamsEncrypted.put("method", mMethodName);
        mBodyParamsEncrypted.put("v", mMethodVersion);
        mBodyParamsEncrypted.put("format", "json");
        mBodyParamsEncrypted.put("sign", EncryptUtil.createSignParam(mBodyParamsEncrypted));
    }
}
