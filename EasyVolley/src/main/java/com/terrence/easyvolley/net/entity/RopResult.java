package com.terrence.easyvolley.net.entity;

/**
 * Created by DarkSouls on 2017/12/2.
 */

import java.util.List;

public class RopResult {

    private int code;
    private String message;
    private String solution;
    private List<ResultDetail> subErrors;

    public boolean isSuccess() {
        return code == 0 && message == null && solution == null;
    }

    public boolean isValidateSession() {
        return code == 20 || code == 21;
    }

    public boolean needToast() {
        return !(code == 20 || code == 21);
    }

    public String getErrorMsg() {
        if (code == 9 || code == 33) {
            if (subErrors != null && !subErrors.isEmpty()) {
                return subErrors.get(0).message;
            } else {
                return message;
            }
        } else if (code == 39) {
            return message;
        } else {
            return message;
        }
    }

    /**
     * 获取子错误 subErrors.code
     */
    public String getSubErrorCode() {
        if (code == 9 || code == 38) {
            if (subErrors != null && !subErrors.isEmpty()) {
                return subErrors.get(0).code;
            } else {
                return "";
            }
        } else {
            return "";
        }
    }
}

