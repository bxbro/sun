package com.bxbro.sun.common.enums;

/**
 * 任务类型枚举
 */
public enum TaskTypeEnum {
    LIFE(0, "生活"), WORK(1, "工作");

    Integer code;
    String desc;
    TaskTypeEnum(Integer code, String desc) {
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
