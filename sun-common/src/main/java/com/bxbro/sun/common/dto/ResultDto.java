package com.bxbro.sun.common.dto;

import com.bxbro.sun.common.enums.SystemEnum;

import java.io.Serializable;

public class ResultDto<T> implements Serializable {
    private Integer code;
    private String msg;
    private T data;

    public ResultDto(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResultDto(T data) {
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
