package com.cmgzs.utils;

import java.io.Serializable;

/**
 * 返回数据给前端的封装类
 *
 * @param <T>
 * @Auther: hzy
 * @Date: 2022/4/28
 */

public class Result<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    //业务码
    private int resultCode;
    //返回信息
    private String message;
    //数据结果，泛型
    private T data;

    public Result() {
    }

    public Result(int resultCode, String message) {
        this.resultCode = resultCode;
        this.message = message;
    }

    public Result(int resultCode, String message, T data) {
        this.resultCode = resultCode;
        this.message = message;
        this.data = data;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
