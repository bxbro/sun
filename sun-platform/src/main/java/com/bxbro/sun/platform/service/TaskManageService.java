package com.bxbro.sun.platform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bxbro.sun.core.model.BaseResult;
import com.bxbro.sun.common.base.domain.entity.TaskManage;
import com.bxbro.sun.platform.domain.request.TaskListRequest;
import com.bxbro.sun.platform.domain.request.UpsertTaskRequest;
import com.bxbro.sun.platform.domain.vo.TaskListDataVO;

public interface TaskManageService extends IService<TaskManage> {
    /**
     * 新建或编辑任务
     * @param request
     */
    BaseResult<Long> upsertTask(UpsertTaskRequest request);

    /**
     * 分页查询任务列表
     * @param request
     * @return
     */
    BaseResult<TaskListDataVO> queryTaskList(TaskListRequest  request);
}
