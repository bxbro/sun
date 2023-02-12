package com.bxbro.sun.common.enums;

/**
 * 业务类枚举
 *
 * @author: dong
 * @date: 2023/2/12 21:50
 * @since: 1.0
 */
public enum BusinessEnum {
    /**--------用户相关----------**/
    USER_ID_NOT_EXIST(1000, "userId not exist."),

    /**--------任务相关----------**/
    TASK_ID_NOT_EXIST(2000, "taskId not exist."),
    TASK_NAME_NOT_EXIST(2001, "taskName not exist."),
    TASK_TYPE_NOT_EXIST(2002, "taskType not exist."),
    DEADLINE_NOT_EXIST(2003, "deadline not exist."),
    CONTENT_NOT_EXIST(2004, "content not exist.")
    ;

    private Integer code;
    private String desc;

    BusinessEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
