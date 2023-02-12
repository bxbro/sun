package com.bxbro.sun.common.exception;

import com.bxbro.sun.common.enums.BusinessEnum;
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
    public SunException(BusinessEnum businessEnum) {
        this.code = businessEnum.getCode();
        this.msg = businessEnum.getDesc();
    }

    public SunException(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
