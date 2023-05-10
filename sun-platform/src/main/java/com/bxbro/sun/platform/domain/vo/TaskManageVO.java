package com.bxbro.sun.platform.domain.vo;

import com.bxbro.sun.platform.config.TaskStatusSerializer;
import com.bxbro.sun.platform.config.TaskTypeSerializer;
import com.bxbro.sun.common.base.domain.entity.TaskManage;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TaskManageVO extends TaskManage {

    @ApiModelProperty("任务类型")
    @JsonSerialize(using = TaskTypeSerializer.class)
    private Integer taskType;

    @ApiModelProperty("任务状态")
    @JsonSerialize(using = TaskStatusSerializer.class)
    private Integer taskStatus;
}
