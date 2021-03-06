package com.terrence.easyvolley.net.callback;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.terrence.easyvolley.net.entity.RopResult;
import com.terrence.easyvolley.net.util.VolleyErrorHelper;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by DarkSouls on 2017/11/20.
 */
public class RequestCallbacks implements Response.Listener<RopResult>, Response.ErrorListener {

    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final ISessionExpired SESSION_EXPIRED;
    private final IToastError TOAST_ERROR;
    private final HashMap<String, IHandleServerError> HANDLE_SERVER_ERROR;
    private final IFailure FAILURE;

    private final boolean NEED_SESSION;

    public RequestCallbacks(IRequest request,
                            ISuccess success,
                            ISessionExpired sessionExpired,
                            IToastError toastError,
                            HashMap<String, IHandleServerError> handleServerError,
                            IFailure failure,
                            boolean needSession) {
        this.REQUEST = request;
        this.SUCCESS = success;
        this.SESSION_EXPIRED = sessionExpired;
        this.TOAST_ERROR = toastError;
        this.HANDLE_SERVER_ERROR = handleServerError;
        this.FAILURE = failure;
        this.NEED_SESSION = needSession;
    }

    @Override
    public void onResponse(RopResult result) {
        if (result.isSuccess()) {
            if (SUCCESS != null)
                SUCCESS.onSuccess(result);
        } else if (NEED_SESSION && result.isValidateSession()) {
            if (SESSION_EXPIRED != null)
                SESSION_EXPIRED.onSessionExpired();
        } else if (result.needToast()) {
            if (TOAST_ERROR != null)
                TOAST_ERROR.onToastError(result.getErrorMsg());
        } else {
            if (HANDLE_SERVER_ERROR != null && HANDLE_SERVER_ERROR.size() > 0) {
                for (Map.Entry<String, IHandleServerError> entry : HANDLE_SERVER_ERROR.entrySet()) {
                    if (entry.getKey().equals(result.getSubErrorCode())) {
                        entry.getValue().onHandleServerError();
                        break;
                    }
                }
            }
        }
        if (REQUEST != null)
            REQUEST.onRequestEnd();
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        if (FAILURE != null)
            FAILURE.onFailure(VolleyErrorHelper.getMessage(error));
        if (REQUEST != null)
            REQUEST.onRequestEnd();
    }

    public ISuccess getSuccessListener() {
        return SUCCESS;
    }
}
