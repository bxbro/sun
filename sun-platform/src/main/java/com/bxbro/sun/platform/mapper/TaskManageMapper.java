package com.bxbro.sun.platform.mapper;

import com.bxbro.sun.platform.domain.entity.TaskManage;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskManageMapper {

    void createTask(TaskManage taskManage);
}
