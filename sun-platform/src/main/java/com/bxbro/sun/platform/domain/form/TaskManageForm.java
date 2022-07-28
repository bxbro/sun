package com.bxbro.sun.platform.domain.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@ApiModel("任务管理表单")
@Data
@NoArgsConstructor
public class TaskManageForm {

    @ApiModelProperty("任务名称")
    @NotBlank(message = "任务名称不能为空")
    private String taskName;

    @ApiModelProperty("任务类型")
    @NotNull(message = "任务类型不能为空")
    private Integer taskType;

    @ApiModelProperty("用户id")
    @NotNull(message = "用户id不能为空")
    private Long userId;

    @ApiModelProperty("截止日期")
    @Future(message = "截止日期必须大于当前时间")
    private Date deadline;

    @ApiModelProperty("任务内容")
    @NotBlank(message = "任务内容不能为空")
    private String content;
}
