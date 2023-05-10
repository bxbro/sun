package com.bxbro.sun.platform.domain.query;

import com.bxbro.sun.common.base.domain.query.BasicQuery;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TaskManageQuery extends BasicQuery {

    @ApiModelProperty("任务名称")
    private String taskName;

    @ApiModelProperty("任务类型 0生活/1工作")
    private Integer taskType;

    @ApiModelProperty("截止日期")
    private String deadline;

    @ApiModelProperty("任务内容")
    private String content;

    @ApiModelProperty("任务状态")
    private Integer taskStatus;
}
