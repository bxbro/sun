package com.bxbro.sun.platform.handler;

import com.bxbro.sun.common.exception.SunException;
import com.bxbro.sun.common.utils.ResultUtil;
import com.bxbro.sun.core.model.BaseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    @ExceptionHandler(SunException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public BaseResult handlerBusinessException(SunException sunException) {
        LOGGER.error("exception happened at {}", sunException.getMsg());
        return ResultUtil.outFail(sunException.getCode(), sunException.getMsg());
    }
}
