package com.bxbro.sun.platform.service;

import com.bxbro.sun.platform.domain.form.TaskManageForm;

public interface TaskManageService {
    /**
     * 创建任务
     * @param form
     */
    void createTask(TaskManageForm form);
}
