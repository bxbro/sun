package com.bxbro.sun.common.base.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 任务管理实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_task_manage")
public class TaskManage {

    private Long id;
    /**
     * 任务名称
     */
    private String taskName;
    /**
     * 任务类型
     */
    private Integer taskType;
    /**
     * 用户id，即任务负责人
     */
    private Long userId;
    /**
     * 任务截止日期
     */
    private Date deadline;
    /**
     * 任务内容
     */
    private String content;
    /**
     * 任务状态
     */
    private Integer taskStatus;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 是否删除
     */
    private Integer delFlag;
}
