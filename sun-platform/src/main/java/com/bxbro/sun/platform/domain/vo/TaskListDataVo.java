package com.bxbro.sun.platform.domain.vo;

import lombok.Data;

import java.util.List;

/**
 * 任务列表data数据返回体
 *
 * @author: dong
 * @date: 2023/2/12 22:23
 * @since: 1.0
 */
@Data
public class TaskListDataVo {
    /**
     * 列表长度
     */
    private Integer count;
    /**
     * 前端VO返回结果
     */
    private List<TaskManageVO> list;
}
