package com.bxbro.sun.platform.service.impl;

import com.bxbro.sun.common.enums.TaskStatusEnum;
import com.bxbro.sun.common.utils.DateUtils;
import com.bxbro.sun.platform.domain.entity.TaskManage;
import com.bxbro.sun.platform.domain.form.TaskManageForm;
import com.bxbro.sun.platform.mapper.TaskManageMapper;
import com.bxbro.sun.platform.service.TaskManageService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class TaskManageServiceImpl implements TaskManageService {

    @Resource
    private TaskManageMapper taskManageMapper;

    @Override
    public void createTask(TaskManageForm form) {
        TaskManage taskManage = new TaskManage();
        BeanUtils.copyProperties(form, taskManage);
        Date deadline = DateUtils.stringToDate(form.getDeadline(), DateUtils.DATE_PATTERN);
        taskManage.setDeadline(deadline);
        taskManage.setTaskStatus(TaskStatusEnum.WAITING_COMPLETE.getCode());
        taskManageMapper.createTask(taskManage);
    }
}
