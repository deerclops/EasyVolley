package com.terrence.easyvolley.net;

import com.android.volley.Request;
import com.terrence.easyvolley.app.Rop;
import com.terrence.easyvolley.net.callback.IFailure;
import com.terrence.easyvolley.net.callback.IHandleServerError;
import com.terrence.easyvolley.net.callback.IRequest;
import com.terrence.easyvolley.net.callback.ISessionExpired;
import com.terrence.easyvolley.net.callback.ISuccess;
import com.terrence.easyvolley.net.callback.IToastError;
import com.terrence.easyvolley.net.callback.RequestCallbacks;
import com.terrence.easyvolley.net.entity.RopResult;

import java.util.HashMap;
import java.util.Map;

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
    private final HashMap<String, IHandleServerError> HANDLE_SERVER_ERROR;
    private final IFailure FAILURE;

    private final boolean NEED_SESSION;

    private RequestCallbacks mReqCallBacks;

    // Params
    private final Map<String, String> HEAD_PARAMS;
    private final Map<String, String> BODY_PARAMS;

    NetEngine(String url,
              String method_name, String method_version,
              IRequest request,
              ISuccess success,
              ISessionExpired session_expired,
              IToastError toast_error,
              HashMap<String, IHandleServerError> handle_server_error,
              IFailure failure,
              Map<String, String> headParams,
              Map<String, String> bodyParams,
              boolean needSession) {
        this.URL = url;
        this.METHOD_NAME = method_name;
        this.METHOD_VERSION = method_version;
        this.REQUEST = request;
        this.SUCCESS = success;
        this.SESSION_EXPIRED = session_expired;
        this.TOAST_ERROR = toast_error;
        this.HANDLE_SERVER_ERROR = handle_server_error;
        this.FAILURE = failure;

        this.HEAD_PARAMS = headParams;
        this.BODY_PARAMS = bodyParams;

        this.NEED_SESSION = needSession;

        mReqCallBacks = new RequestCallbacks(REQUEST, SUCCESS, SESSION_EXPIRED, TOAST_ERROR, HANDLE_SERVER_ERROR, FAILURE, NEED_SESSION);
    }

    public static NetEngineBuilder builder() {
        return new NetEngineBuilder();
    }

    public final void fetch() {
        if (REQUEST != null) {
            REQUEST.onRequestBegin();
        }
        RopRequest req = new RopRequest(Request.Method.POST, URL, mReqCallBacks);
        req.setHeadParamsMap(HEAD_PARAMS);
        req.setReqParamsMap(BODY_PARAMS);
        Rop.getRequestQueue().add(req);
    }
}
