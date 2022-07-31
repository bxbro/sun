package com.bxbro.sun.platform.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bxbro.sun.platform.domain.entity.TaskManage;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskManageMapper extends BaseMapper<TaskManage> {

    void createTask(TaskManage taskManage);
}
