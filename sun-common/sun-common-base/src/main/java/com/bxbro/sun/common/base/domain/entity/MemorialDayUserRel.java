package com.bxbro.sun.common.base.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * <p>
 * 纪念日用户映射实体类
 * </p>
 *
 * @author auto-generator
 * @since 2023-05-13
 */
@Data
@TableName("t_memorial_day_user_rel")
public class MemorialDayUserRel {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 纪念日表的主键
     */
    private Long memorialDayId;

    /**
     * 需要通知的用户的id
     */
    private Long userId;

}
