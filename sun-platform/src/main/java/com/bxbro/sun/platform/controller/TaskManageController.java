package com.bxbro.sun.platform.controller;

import com.bxbro.sun.common.domain.dto.ResultDto;
import com.bxbro.sun.common.utils.ResultUtil;
import com.bxbro.sun.platform.domain.form.TaskManageForm;
import com.bxbro.sun.platform.domain.query.TaskManageQuery;
import com.bxbro.sun.platform.service.TaskManageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.http.util.Asserts;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@Api(tags = "任务管理controller")
@RestController
@RequestMapping("/task/api")
public class TaskManageController {

    @Resource
    private TaskManageService taskManageService;


    @ApiOperation("新建任务")
    @PostMapping("/v1/new")
    public ResultDto createTask(@Validated TaskManageForm form) {
        taskManageService.createTask(form);
        return ResultUtil.outSuccess();
    }

    @ApiOperation("删除任务")
    @DeleteMapping("/v1/{id}")
    public ResultDto deleteTask(@PathVariable("id") Long id) {
        taskManageService.removeById(id);
        return ResultUtil.outSuccess();
    }

    @ApiOperation("查询任务列表")
    @GetMapping("/v1/list")
    public ResultDto queryTaskList(TaskManageQuery query) {
        Asserts.notNull(query.getPageNo(), "页码不能为空.");
        Asserts.notNull(query.getPageSize(), "页数不能为空.");
        return ResultUtil.outSuccess(taskManageService.queryTaskList(query));
    }

}