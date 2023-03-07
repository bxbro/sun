package com.bxbro.sun.common.domain;

import com.bxbro.sun.common.enums.SystemEnum;

/**
 * 统一封装响应体
 * @param <T>
 */
public class BaseResult<T> extends BaseModel {
    private Integer code;
    private String msg;
    private T data;

    public BaseResult() {
    }

    public BaseResult(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public BaseResult(T data) {
        this.code = SystemEnum.SUCCESS.getCode();
        this.msg = SystemEnum.SUCCESS.getDesc();
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
