package com.bxbro.sun.platform.domain.request;

import com.bxbro.sun.common.domain.BaseRequest;
import lombok.Data;

/**
 * 任务列表请求体
 *
 * @author: dong
 * @date: 2023/2/12 22:14
 * @since: 1.0
 */
@Data
public class TaskListRequest extends BaseRequest {

    /**
     * 页码
     */
    private Integer pageNo;
    /**
     * 页数
     */
    private Integer pageSize;

    private String taskName;

    private Integer taskType;

    private String deadline;

    private String content;

    private Integer taskStatus;
}
