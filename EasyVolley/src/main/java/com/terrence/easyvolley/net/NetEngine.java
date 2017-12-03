package com.terrence.easyvolley.net;

import com.android.volley.Request;
import com.terrence.easyvolley.net.callback.IFailure;
import com.terrence.easyvolley.net.callback.IHandleServerError;
import com.terrence.easyvolley.net.callback.IRequest;
import com.terrence.easyvolley.net.callback.ISessionExpired;
import com.terrence.easyvolley.net.callback.ISuccess;
import com.terrence.easyvolley.net.callback.IToastError;
import com.terrence.easyvolley.net.callback.RequestCallbacks;
import com.terrence.easyvolley.net.entity.RopResult;

/**
 * Created by DarkSouls on 2017/12/1.
 */

public final class NetEngine {

    private final String URL;
    private final String METHOD_NAME;
    private final String METHOD_VERSION;

    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final ISessionExpired SESSION_EXPIRED;
    private final IToastError TOAST_ERROR;
    private final IHandleServerError HANDLE_SERVER_ERROR;
    private final IFailure FAILURE;

    private RequestCallbacks mReqCallBacks;

    NetEngine(String URL,
                     String METHOD_NAME, String METHOD_VERSION,
                     IRequest REQUEST,
                     ISuccess SUCCESS,
                     ISessionExpired SESSION_EXPIRED,
                     IToastError TOAST_ERROR,
                     IHandleServerError HANDLE_SERVER_ERROR,
                     IFailure FAILURE) {
        this.URL = URL;
        this.METHOD_NAME = METHOD_NAME;
        this.METHOD_VERSION = METHOD_VERSION;
        this.REQUEST = REQUEST;
        this.SUCCESS = SUCCESS;
        this.SESSION_EXPIRED = SESSION_EXPIRED;
        this.TOAST_ERROR = TOAST_ERROR;
        this.HANDLE_SERVER_ERROR = HANDLE_SERVER_ERROR;
        this.FAILURE = FAILURE;
    }

    public static NetEngineBuilder builder() {
        return new NetEngineBuilder();
    }

    private RequestCallbacks getReqCallBacks() {
        if (mReqCallBacks == null) {
            mReqCallBacks = new RequestCallbacks(REQUEST, SUCCESS, SESSION_EXPIRED, TOAST_ERROR, HANDLE_SERVER_ERROR, FAILURE);
        }
        return mReqCallBacks;
    }

    public final <T extends RopResult> void fetch(Class<T> clazz) {
        RopRequest req = new RopRequest(Request.Method.POST, URL, clazz, mReqCallBacks, mReqCallBacks);
//        req.setHeadParamsMap();
//        req.setReqParamsMap();

    }
}
