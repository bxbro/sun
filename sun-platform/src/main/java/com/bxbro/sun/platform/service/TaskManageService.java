package com.bxbro.sun.platform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bxbro.sun.core.model.BaseResult;
import com.bxbro.sun.platform.domain.entity.TaskManage;
import com.bxbro.sun.platform.domain.request.TaskListRequest;
import com.bxbro.sun.platform.domain.request.UpsertTaskRequest;

public interface TaskManageService extends IService<TaskManage> {
    /**
     * 新建或编辑任务
     * @param request
     */
    BaseResult upsertTask(UpsertTaskRequest request);

    /**
     * 分页查询任务列表
     * @param request
     * @return
     */
    BaseResult queryTaskList(TaskListRequest  request);
}
