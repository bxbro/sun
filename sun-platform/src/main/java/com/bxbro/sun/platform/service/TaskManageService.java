package com.bxbro.sun.platform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bxbro.sun.platform.domain.entity.TaskManage;
import com.bxbro.sun.platform.domain.form.TaskManageForm;
import com.bxbro.sun.platform.domain.query.TaskManageQuery;
import com.bxbro.sun.platform.domain.vo.TaskManageVO;

import java.util.List;

public interface TaskManageService extends IService<TaskManage> {
    /**
     * 创建任务
     * @param form
     */
    void createTask(TaskManageForm form);

    /**
     * 分页查询任务列表
     * @param query
     * @return
     */
    List<TaskManageVO> queryTaskList(TaskManageQuery query);

    /**
     * 编辑任务
     * @param form
     */
    void updateTask(TaskManageForm form);
}
