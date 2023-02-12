package com.bxbro.sun.common.utils;

import com.bxbro.sun.common.domain.BaseResult;
import com.bxbro.sun.common.enums.SystemEnum;

public class ResultUtil {

    public static <R> BaseResult<R> outSuccess() {
        return new BaseResult<>(SystemEnum.SUCCESS.getCode(), SystemEnum.SUCCESS.getDesc(), null);
    }
    public static <R> BaseResult<R> outSuccess(R data) {
        return new BaseResult<>(data);
    }

    public static <R> BaseResult<R> outFail(String errorMsg) {
        return new BaseResult<>(SystemEnum.FAIL.getCode(), errorMsg, null);
    }
    public static <R> BaseResult<R> outFail(Integer errorCode, String errorMsg) {
        return new BaseResult<>(errorCode, errorMsg, null);
    }
}
