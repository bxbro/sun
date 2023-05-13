package com.bxbro.sun.notice.domain.dto;

import com.bxbro.sun.common.base.enums.DateTypeEnum;
import lombok.Data;

/**
 * TODO
 *
 * @author: dong
 * @date: 2023/5/11 22:57
 * @since: 1.0
 */
@Data
public class MemorialDayDto {

    private Long id;
    /**
     * 纪念日名称
     */
    private String memorialDayName;

    /**
     * 纪念日日期
     */
    private String memorialDayDate;

    /**
     * 日期类型
     */
    private DateTypeEnum dateType;

    /**
     * 通知的时间点
     */
    private String noticeTimePoints;

    /**
     * 备注
     */
    private String remark;
}
