package com.bxbro.sun.platform.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bxbro.sun.common.base.domain.entity.TaskManage;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskManageMapper extends BaseMapper<TaskManage> {

    Long createTask(TaskManage taskManage);

    void updateTask(TaskManage taskManage);
}
