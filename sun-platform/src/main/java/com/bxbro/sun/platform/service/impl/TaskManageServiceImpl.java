package com.bxbro.sun.platform.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bxbro.sun.common.domain.BaseResult;
import com.bxbro.sun.common.enums.BusinessEnum;
import com.bxbro.sun.common.enums.LogicEnum;
import com.bxbro.sun.common.enums.SystemEnum;
import com.bxbro.sun.common.enums.TaskStatusEnum;
import com.bxbro.sun.common.exception.SunException;
import com.bxbro.sun.common.utils.AssertUtils;
import com.bxbro.sun.common.utils.DateUtils;
import com.bxbro.sun.common.utils.ResultUtil;
import com.bxbro.sun.core.template.ServiceDelegator;
import com.bxbro.sun.core.template.ServiceTemplate;
import com.bxbro.sun.platform.domain.entity.TaskManage;
import com.bxbro.sun.platform.domain.request.TaskListRequest;
import com.bxbro.sun.platform.domain.request.UpsertTaskRequest;
import com.bxbro.sun.platform.domain.vo.TaskListDataVo;
import com.bxbro.sun.platform.domain.vo.TaskManageVO;
import com.bxbro.sun.platform.mapper.TaskManageMapper;
import com.bxbro.sun.platform.service.TaskManageService;
import org.apache.commons.lang3.StringUtils;
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
    public BaseResult upsertTask(UpsertTaskRequest request) {
        return ServiceTemplate.doService(request, new ServiceDelegator<UpsertTaskRequest, BaseResult>() {
            @Override
            public BaseResult initResult() {
                return new BaseResult();
            }

            @Override
            public void paramValidate(UpsertTaskRequest req) throws SunException {
                checkParam(req);
            }

            @Override
            public BaseResult doService(UpsertTaskRequest req) throws SunException {
                Long taskId;
                // 新增
                if (req.getId() == null) {
                    TaskManage taskManage = new TaskManage();
                    BeanUtils.copyProperties(req, taskManage);
                    Date deadline = DateUtils.stringToDate(req.getDeadline(), DateUtils.DATE_PATTERN);
                    taskManage.setDeadline(deadline);
                    taskManage.setTaskStatus(TaskStatusEnum.WAITING_COMPLETE.getCode());
                    taskManageMapper.createTask(taskManage);
                    taskId = taskManage.getId();
                }
                // 编辑
                else {
                    TaskManage taskManage = taskManageMapper.selectById(req.getId());
                    AssertUtils.notNull(taskManage, "taskId有误，数据库中不存在该任务.");
                    BeanUtils.copyProperties(req, taskManage);
                    taskManageMapper.updateTask(taskManage);
                    taskId = req.getId();
                }
                return ResultUtil.outSuccess(taskId);
            }
        });
    }

    /**
     * 校验请求入参
     * @param req
     */
    private void checkParam(UpsertTaskRequest req) {
        if (StringUtils.isEmpty(req.getTaskName())) {
            throw new SunException(BusinessEnum.TASK_NAME_NOT_EXIST);
        }
        if (req.getTaskType() == null) {
            throw new SunException(BusinessEnum.TASK_TYPE_NOT_EXIST);
        }
        if (StringUtils.isEmpty(req.getDeadline())) {
            throw new SunException(BusinessEnum.DEADLINE_NOT_EXIST);
        }
        if (StringUtils.isEmpty(req.getContent())) {
            throw new SunException(BusinessEnum.CONTENT_NOT_EXIST);
        }
        if (req.getUserId() == null) {
            throw new SunException(BusinessEnum.USER_ID_NOT_EXIST);
        }
    }

    @Override
    public BaseResult queryTaskList(TaskListRequest request) {
        return ServiceTemplate.doService(request, new ServiceDelegator<TaskListRequest, BaseResult>() {
            @Override
            public BaseResult initResult() {
                return new BaseResult();
            }

            @Override
            public void paramValidate(TaskListRequest req) throws SunException {
                if (req.getPageNo() == null || req.getPageSize() == null) {
                    throw new SunException(SystemEnum.PARAM_ILLEGAL);
                }
            }

            @Override
            public BaseResult doService(TaskListRequest req) throws SunException {
                TaskManage taskManage = new TaskManage();
                BeanUtils.copyProperties(req, taskManage);

                IPage<TaskManage> page = new Page<>(req.getPageNo(), req.getPageSize());
                QueryWrapper<TaskManage> wrapper = buildQueryWrapper(req, taskManage);

                page = taskManageMapper.selectPage(page, wrapper);
                if (page == null) {
                    return new BaseResult();
                }
                List<TaskManageVO> voList = new ArrayList<>();
                page.getRecords().forEach(e -> {
                    TaskManageVO vo = new TaskManageVO();
                    BeanUtils.copyProperties(e, vo);
                    voList.add(vo);
                });

                TaskListDataVo dataVo = new TaskListDataVo();
                dataVo.setList(voList);
                dataVo.setCount(voList.size());
                return ResultUtil.outSuccess(dataVo);
            }
        });
    }

    /**
     * 构建查询条件
     * @param req
     * @param taskManage
     * @return
     */
    private QueryWrapper<TaskManage> buildQueryWrapper(TaskListRequest req, TaskManage taskManage) {
        QueryWrapper<TaskManage> wrapper = new QueryWrapper<>(taskManage);
        wrapper.eq("del_flag", LogicEnum.UNDELETE.getCode());

        // 先按照任务状态升序排序，即<待完成>在前，<已完成>在后
        wrapper.orderByAsc("task_status");
        // 再按照任务的创建时间降序排序
        wrapper.orderByDesc("create_time");

        if (StringUtils.isNotEmpty(req.getTaskName())) {
            wrapper.like("task_name", req.getTaskName());
        }
        if (req.getTaskType() != null) {
            wrapper.eq("task_type", req.getTaskType());
        }
        return wrapper;
    }

}
