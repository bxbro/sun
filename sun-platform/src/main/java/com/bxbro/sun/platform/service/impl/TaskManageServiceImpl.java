package com.bxbro.sun.platform.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bxbro.sun.common.domain.BaseResult;
import com.bxbro.sun.common.enums.TaskStatusEnum;
import com.bxbro.sun.common.exception.SunException;
import com.bxbro.sun.common.utils.AssertUtils;
import com.bxbro.sun.common.utils.DateUtils;
import com.bxbro.sun.common.utils.ResultUtil;
import com.bxbro.sun.core.template.ServiceDelegator;
import com.bxbro.sun.core.template.ServiceTemplate;
import com.bxbro.sun.platform.domain.entity.TaskManage;
import com.bxbro.sun.platform.domain.form.TaskManageForm;
import com.bxbro.sun.platform.domain.query.TaskManageQuery;
import com.bxbro.sun.platform.domain.request.TaskManageRequest;
import com.bxbro.sun.platform.domain.vo.TaskManageVO;
import com.bxbro.sun.platform.mapper.TaskManageMapper;
import com.bxbro.sun.platform.service.TaskManageService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TaskManageServiceImpl extends ServiceImpl<TaskManageMapper, TaskManage> implements TaskManageService {

    @Resource
    private TaskManageMapper taskManageMapper;

    @Override
    public BaseResult createTask(TaskManageRequest request) {
        return ServiceTemplate.doService(request, new ServiceDelegator<TaskManageRequest, BaseResult>() {
            @Override
            public BaseResult initResult() {
                return new BaseResult();
            }

            @Override
            public void checkRequestParam(TaskManageRequest req) throws SunException {
                // todo
            }

            @Override
            public BaseResult doService(TaskManageRequest req) throws SunException {
                TaskManage taskManage = new TaskManage();
                BeanUtils.copyProperties(req, taskManage);
                Date deadline = DateUtils.stringToDate(req.getDeadline(), DateUtils.DATE_PATTERN);
                taskManage.setDeadline(deadline);
                taskManage.setTaskStatus(TaskStatusEnum.WAITING_COMPLETE.getCode());
                taskManageMapper.createTask(taskManage);
                return ResultUtil.outSuccess(taskManage.getId());
            }
        });
    }

    @Override
    public List<TaskManageVO> queryTaskList(TaskManageQuery query) {
        TaskManage taskManage = new TaskManage();
        BeanUtils.copyProperties(query, taskManage);

        IPage<TaskManage> page = new Page<>(query.getPageNo(), query.getPageSize());
        QueryWrapper<TaskManage> wrapper = new QueryWrapper<>(taskManage);
//        wrapper.like("task_name", query.getTaskName());

        page = taskManageMapper.selectPage(page, wrapper);
        List<TaskManage> taskManageList = page.getRecords();
        List<TaskManageVO> voList = new ArrayList<>();
        taskManageList.forEach(e -> {
            TaskManageVO vo = new TaskManageVO();
            BeanUtils.copyProperties(e, vo);
            voList.add(vo);
        });
        return voList;
    }

    @Override
    public void updateTask(TaskManageForm form) {
        TaskManage task = taskManageMapper.selectById(form.getId());
        AssertUtils.notNull(task, "任务id有误，数据库中不存在该任务.");
        BeanUtils.copyProperties(form, task);
        taskManageMapper.updateTask(task);
    }
}
