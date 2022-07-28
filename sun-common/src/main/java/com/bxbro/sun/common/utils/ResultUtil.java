package com.bxbro.sun.common.utils;

import com.bxbro.sun.common.Dto.ResultDto;
import com.bxbro.sun.common.enums.SystemEnum;

public class ResultUtil {

    public static <R> ResultDto<R> outSuccess() {
        return new ResultDto<>(SystemEnum.SUCCESS.getCode(), SystemEnum.SUCCESS.getDesc(), null);
    }
    public static <R> ResultDto<R> outSuccess(R data) {
        return new ResultDto<>(data);
    }

    public static <R> ResultDto<R> outFail(String errorMsg) {
        return new ResultDto<>(SystemEnum.FAIL.getCode(), errorMsg, null);
    }
    public static <R> ResultDto<R> outFail(Integer errorCode, String errorMsg) {
        return new ResultDto<>(errorCode, errorMsg, null);
    }
}
