package com.repay.app.controller.base;

import java.io.Serializable;

public class Status implements Serializable {
    private static final long serialVersionUID = 1L;

    private boolean success = false;
    private String code;
    private String msg;

    public Status() {

    }

    public Status(boolean success, String code, String msg) {
        this.success = success;
        this.code = code;
        this.msg = msg;
    }

    public Status(boolean success, String code) {
        this.success = success;
        this.code = code;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMessage(String msg) {
        this.msg = msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
