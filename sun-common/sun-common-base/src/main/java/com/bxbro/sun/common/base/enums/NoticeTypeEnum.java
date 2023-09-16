package com.bxbro.sun.common.base.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 通知类型枚举
 *
 * @author: dong
 * @date: 2023/9/16 17:19
 * @since: 1.0
 */
@AllArgsConstructor
public enum NoticeTypeEnum {
    /**
     * 邮件
     */
    MAIL(1, "mail"),
    /**
     * 短信
     */
    SHORT_MESSAGE(2, "short-message");

    @Getter
    private Integer code;
    @Getter
    private String desc;

}
