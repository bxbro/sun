package com.bxbro.sun.common.enums;

/**
 * 逻辑枚举
 */
public enum LogicEnum {
    UNDELETE(0, "未删除"), DELETED(1, "已删除");
    Integer code;
    String desc;

    LogicEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
