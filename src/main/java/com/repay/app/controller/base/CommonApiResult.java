package com.repay.app.controller.base;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.io.Serializable;

public class CommonApiResult<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private Status status;
    private T data;

    private CommonApiResult(){

    }

    public CommonApiResult(Status status, T data) {
        this.status = status;
        this.data = data;
    }

    public CommonApiResult(Status status) {
        this.status = status;
    }

    public static <T> CommonApiResult<T> success(T result) {
        Status status = new Status(true, ErrorCode.SUCCESS.getErrCode(), ErrorCode.SUCCESS.getErrMsg());
        CommonApiResult<T> item = new CommonApiResult<T>(status, result);
        return item;
    }

    public static <T> CommonApiResult<T> failure(String errCode, T result) {
        Status status = new Status(false, errCode, "");
        CommonApiResult<T> item = new CommonApiResult<T>(status,result);
        return item;
    }

    public static <T> CommonApiResult<T> success(String errCode, String errMsg) {
        Status status = new Status(true, errCode, errMsg);
        CommonApiResult<T> item = new CommonApiResult<T>(status);
        return item;
    }

    public static <T> CommonApiResult<T> success() {
        Status status = new Status(true, ErrorCode.SUCCESS.getErrCode(), ErrorCode.SUCCESS.getErrMsg());
        CommonApiResult<T> item = new CommonApiResult<T>(status);
        return item;
    }

    public static <T> CommonApiResult<T> failure(String errorCode, String errorMessage) {
        Status status = new Status(false, errorCode, errorMessage);
        CommonApiResult<T> item = new CommonApiResult<T>(status);
        return item;
    }

    public static <T> CommonApiResult<T> failure(Status status) {
        CommonApiResult<T> item = new CommonApiResult<T>(status);
        return item;
    }

    public static <T> CommonApiResult<T> failure(String errCode, BindingResult validResult) {
        final FieldError fieldError = validResult.getFieldError();
        final String msg = fieldError.getDefaultMessage();
        Status status = new Status(false, errCode, msg);
        CommonApiResult<T> item = new CommonApiResult<T>(status);
        return item;
    }

    public static <T> CommonApiResult<T> failure(String msg) {
        Status status = new Status(false, ErrorCode.SYS_ERROR.getErrCode(), msg);
        CommonApiResult<T> item = new CommonApiResult<T>(status);
        return item;
    }

    public static <T> CommonApiResult<T> failure(BindingResult bindingResult) {
        final FieldError fieldError = bindingResult.getFieldError();
        final String code = ErrorCode.DATA_VALIDATE.getErrCode();
        final String msg = fieldError.getDefaultMessage();
        Status status = new Status(false, code, msg);
        CommonApiResult<T> item = new CommonApiResult<T>(status);
        return item;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
