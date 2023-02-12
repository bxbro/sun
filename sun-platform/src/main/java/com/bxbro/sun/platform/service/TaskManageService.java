package com.bxbro.sun.platform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bxbro.sun.common.domain.BaseResult;
import com.bxbro.sun.platform.domain.entity.TaskManage;
import com.bxbro.sun.platform.domain.form.TaskManageForm;
import com.bxbro.sun.platform.domain.query.TaskManageQuery;
import com.bxbro.sun.platform.domain.request.TaskManageRequest;
import com.bxbro.sun.platform.domain.vo.TaskManageVO;

import java.util.List;

public interface TaskManageService extends IService<TaskManage> {
    /**
     * 新建或编辑任务
     * @param request
     */
    BaseResult upsertTask(TaskManageRequest request);

    /**
     * 分页查询任务列表
     * @param query
     * @return
     */
    List<TaskManageVO> queryTaskList(TaskManageQuery query);
}
