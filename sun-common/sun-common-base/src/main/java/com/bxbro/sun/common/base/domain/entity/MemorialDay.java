package com.bxbro.sun.common.base.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.bxbro.sun.common.base.enums.DateTypeEnum;
import com.bxbro.sun.common.base.enums.LogicEnum;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * <p>
 *  纪念日 表
 * </p>
 *
 * @author auto-generator
 * @since 2023-05-11
 */
@Data
@TableName("t_memorial_day")
public class MemorialDay {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
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

    /**
     * 创建日期
     */
    private LocalDateTime createTime;

    /**
     * 更新日期
     */
    private LocalDateTime updateTime;

    /**
     * 删除标志位
     */
    private LogicEnum delFlag;

}
