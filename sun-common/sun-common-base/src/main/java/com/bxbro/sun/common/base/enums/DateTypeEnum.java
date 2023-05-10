package com.bxbro.sun.common.base.enums;

/**
 * 日期类型枚举
 *
 * @author: dong
 * @date: 2023/5/10 22:45
 * @since: 1.0
 */
public enum DateTypeEnum {

    LUNAR_CALENDAR(0, "农历"),
    SOLAR_CALENDAR(1, "阳历");

    private Integer code;
    private String desc;

    DateTypeEnum(Integer code, String desc) {
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
