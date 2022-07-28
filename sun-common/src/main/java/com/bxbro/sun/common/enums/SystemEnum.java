package com.bxbro.sun.common.enums;

/**
 * 系统枚举
 */
public enum SystemEnum {
    SUCCESS(0, "success"),
    FAIL(-1, "fail"),
    OTHER(999, "Unknown Exception.");

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
