package com.bxbro.sun.platform.controller;

import com.bxbro.sun.common.dto.ResultDto;
import com.bxbro.sun.common.utils.ResultUtil;
import com.bxbro.sun.platform.domain.form.TaskManageForm;
import com.bxbro.sun.platform.service.TaskManageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@Api(tags = "任务管理controller")
@RestController
@RequestMapping("/task/api/v1")
public class TaskManageController {

    @Resource
    private TaskManageService taskManageService;


    @ApiOperation("新建任务")
    @PostMapping("/new")
    public ResultDto createTask(@Validated TaskManageForm form) {
        taskManageService.createTask(form);
        return ResultUtil.outSuccess();
    }
}