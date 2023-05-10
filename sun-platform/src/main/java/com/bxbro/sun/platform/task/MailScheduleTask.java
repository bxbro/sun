package com.bxbro.sun.platform.task;

import com.bxbro.sun.common.base.domain.dto.MailDto;
import com.bxbro.sun.common.base.enums.TaskStatusEnum;
import com.bxbro.sun.common.tools.utils.DateUtils;
import com.bxbro.sun.platform.config.MailConfig;
import com.bxbro.sun.common.base.domain.entity.TaskManage;
import com.bxbro.sun.platform.mapper.TaskManageMapper;
import com.bxbro.sun.platform.service.feign.NoticeFeign;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Description: 邮件定时任务
 * @Author: dong
 * @Date 2022/8/12 22:04
 * @Since 1.0
 */
@Component
public class MailScheduleTask {

    @Resource
    private TaskManageMapper taskManageMapper;
    @Resource
    private NoticeFeign noticeFeign;
    @Resource
    private MailConfig config;


    @XxlJob(value = "testXxlJobHandler")
    public void testXxlJob() {
        XxlJobHelper.log("this is a serious test for xxl-job frame.");
    }


    @XxlJob(value = "noticeTaskHandler")
    public void noticeTaskHandler() {
        XxlJobHelper.log("=======MailScheduleTask begin execute. ========");
        // 1.获取待完成的任务列表
        List<TaskManage> taskList = taskManageMapper.selectList(null);
        taskList = taskList.stream()
                .filter(e-> TaskStatusEnum.WAITING_COMPLETE.getCode().equals(e.getTaskStatus()))
                        .collect(Collectors.toList());
        if (CollectionUtils.isEmpty(taskList)) {
            XxlJobHelper.log("========数据库中不存在待完成的任务. ScheduleTask End. ==========");
            return;
        }

        Set<String> nameSet = taskList.stream().map(TaskManage::getTaskName).collect(Collectors.toSet());
        XxlJobHelper.log("待完成的任务列表有:【{}】", Arrays.toString(nameSet.toArray()));

        // 2.遍历该list，将当前日期与每个task的deadline进行比较。
        for (TaskManage task : taskList) {
            if (null == task || null == task.getDeadline()) {
                continue;
            }
            String deadlineStr = DateUtils.format(task.getDeadline(), DateUtils.DATE_PATTERN);
            Date deadline = DateUtils.stringToDate(deadlineStr, DateUtils.DATE_PATTERN);

            String currentDateStr = DateUtils.format(new Date(), DateUtils.DATE_PATTERN);
            Date currentDate = DateUtils.stringToDate(currentDateStr, DateUtils.DATE_PATTERN);

            // 离deadline还有8天的时候
            if (StringUtils.compare(currentDateStr, deadlineStr) < 0 && DateUtils.calcDiffValue(currentDate, deadline) == 8L) {
                XxlJobHelper.log("Attention !!! The task [{}] remains only 8 days!!!", task.getTaskName());
                // 发邮件提醒 to me
                MailDto mailDto = new MailDto(task.getTaskName(), task.getContent(), config.getFromAddress(), config.getFromAddress());
                noticeFeign.sendMail(mailDto);

            } else if (StringUtils.compare(currentDateStr, deadlineStr) == 0) {
                XxlJobHelper.log("Already send e-mail to her!!! TaskName is:[{}]", task.getTaskName());
                // 发送邮件祝福 to her
                MailDto mailDto = new MailDto(task.getTaskName(), config.getBirthdayText(), config.getToAddress(), config.getFromAddress());
                noticeFeign.sendMail(mailDto);

            } else if(StringUtils.compare(currentDateStr, deadlineStr) > 0) {
                XxlJobHelper.log("The task [{}] has been delayed.", task.getTaskName());
                // 将task设置为已超期
                task.setTaskStatus(TaskStatusEnum.DELAYED.getCode());
                taskManageMapper.updateTask(task);
            }
        }
        XxlJobHelper.log("=======MailScheduleTask execute end. ========");
    }
}
