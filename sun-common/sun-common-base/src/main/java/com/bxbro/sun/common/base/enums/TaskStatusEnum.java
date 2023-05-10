package com.bxbro.sun.common.base.enums;

/**
 * 任务状态枚举
 */
public enum TaskStatusEnum {
    WAITING_COMPLETE(0, "待完成"),
    COMPLETED(1, "已完成"),
    DELAYED(2, "已延期");

    private Integer code;
    private String desc;

    TaskStatusEnum(Integer code, String desc) {
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
