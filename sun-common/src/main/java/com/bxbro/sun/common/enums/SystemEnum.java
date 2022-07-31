package com.bxbro.sun.common.enums;

/**
 * 系统枚举
 */
public enum SystemEnum {
    SUCCESS(0, "success"),
    FAIL(-1, "fail"),
    PARAMETER_EXCEPTION(100, "参数异常!"),
    SERVICE_TIME_OUT(1000, "服务间调用超时"),
    UNEXPECTED_EXCEPTION(500, "系统内部错误，请联系管理员！"),
    OTHER(9999, "Unknown Exception.");

    private Integer code;
    private String desc;

    SystemEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
