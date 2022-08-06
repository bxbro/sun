package com.bxbro.sun.platform.controller;

import com.bxbro.sun.common.domain.dto.ResultDto;
import com.bxbro.sun.common.utils.AssertUtils;
import com.bxbro.sun.common.utils.ResultUtil;
import com.bxbro.sun.platform.domain.form.TaskManageForm;
import com.bxbro.sun.platform.domain.query.TaskManageQuery;
import com.bxbro.sun.platform.domain.vo.TaskManageVO;
import com.bxbro.sun.platform.service.TaskManageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.http.util.Asserts;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


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
    public ResultDto<List<TaskManageVO>> queryTaskList(TaskManageQuery query) {
        AssertUtils.notNull(query.getPageNo(), "pageNo不能为空.");
        AssertUtils.notNull(query.getPageSize(), "pageSize不能为空.");
        return ResultUtil.outSuccess(taskManageService.queryTaskList(query));
    }


    @ApiOperation("编辑任务")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "任务id", required = true, paramType = "path", dataType = "Integer")
    })
    @PutMapping("/v1/{id}")
    public ResultDto updateTask(@PathVariable("id") Long taskId, @Validated TaskManageForm form) {
        AssertUtils.notNull(taskId, "taskId不能为空.");
        form.setId(taskId);
        taskManageService.updateTask(form);
        return ResultUtil.outSuccess();
    }

}