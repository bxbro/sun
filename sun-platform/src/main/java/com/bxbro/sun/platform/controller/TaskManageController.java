package com.bxbro.sun.platform.controller;

import com.bxbro.sun.common.utils.ResultUtil;
import com.bxbro.sun.core.model.BaseResult;
import com.bxbro.sun.platform.domain.form.TaskManageForm;
import com.bxbro.sun.platform.domain.query.TaskManageQuery;
import com.bxbro.sun.platform.domain.request.TaskListRequest;
import com.bxbro.sun.platform.domain.request.UpsertTaskRequest;
import com.bxbro.sun.platform.service.TaskManageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@Api(tags = "任务管理controller")
@RestController
@RequestMapping("/task/api")
public class TaskManageController {

    @Resource
    private TaskManageService taskManageService;


    @ApiOperation("新建或编辑任务")
    @PostMapping("/v1/upsert")
    public BaseResult upsertTask(@Validated TaskManageForm form) {
        UpsertTaskRequest upsertTaskRequest = new UpsertTaskRequest();
        BeanUtils.copyProperties(form, upsertTaskRequest);
        return taskManageService.upsertTask(upsertTaskRequest);
    }

    @ApiOperation("删除任务")
    @DeleteMapping("/v1/{id}")
    public BaseResult deleteTask(@PathVariable("id") Long id) {
        taskManageService.removeById(id);
        return ResultUtil.outSuccess();
    }

    @ApiOperation("查询任务列表")
    @GetMapping("/v1/list")
    public BaseResult queryTaskList(TaskManageQuery query) {
        TaskListRequest taskListRequest = new TaskListRequest();
        BeanUtils.copyProperties(query, taskListRequest);
        return taskManageService.queryTaskList(taskListRequest);
    }

}