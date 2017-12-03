package com.terrence.easyvolley.net.callback;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.terrence.easyvolley.net.entity.RopResult;
import com.terrence.easyvolley.net.util.VolleyErrorHelper;

/**
 * Created by DarkSouls on 2017/11/20.
 */
public class RequestCallbacks implements Response.Listener<RopResult>, Response.ErrorListener {

    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final ISessionExpired SESSION_EXPIRED;
    private final IToastError TOAST_ERROR;
    private final IHandleServerError HANDLE_SERVER_ERROR;
    private final IFailure FAILURE;

    public RequestCallbacks(IRequest request,
                            ISuccess success,
                            ISessionExpired sessionExpired,
                            IToastError toastError,
                            IHandleServerError handleServerError,
                            IFailure failure) {
        this.REQUEST = request;
        this.SUCCESS = success;
        this.SESSION_EXPIRED = sessionExpired;
        this.TOAST_ERROR = toastError;
        this.HANDLE_SERVER_ERROR = handleServerError;
        this.FAILURE = failure;
    }

    @Override
    public void onResponse(RopResult result) {
        if (result.isSuccess()) {
            if (SUCCESS!=null)
                SUCCESS.onSuccess();
            // FIXME: 2017/12/2
        } else if (result.isValidateSession()) {
            if(SESSION_EXPIRED!=null)
                SESSION_EXPIRED.onSessionExpired();
        } else if (result.needToast()) {
            if(TOAST_ERROR!=null)
                TOAST_ERROR.onToastError();
        } else {
//            if()
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

}
