package com.bxbro.sun.common.exception;

import com.bxbro.sun.common.enums.SystemEnum;
import lombok.Data;

/**
 * 自定义业务异常
 */
@Data
public class SunException extends RuntimeException {

    private Integer code;
    private String msg;

    public SunException(SystemEnum systemEnum) {
        this.code = systemEnum.getCode();
        this.msg = systemEnum.getDesc();
    }
    public SunException(String msg) {
        this.code = SystemEnum.FAIL.getCode();
        this.msg = msg;
    }
}
