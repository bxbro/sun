package com.bxbro.sun.platform.task;

import cn.hutool.core.text.CharSequenceUtil;
import com.bxbro.sun.common.base.domain.dto.CommonMessageDTO;
import com.bxbro.sun.common.base.domain.dto.MailDTO;
import com.bxbro.sun.common.base.domain.dto.ShortMessageDTO;
import com.bxbro.sun.common.base.domain.entity.TaskManage;
import com.bxbro.sun.common.base.enums.BusinessEnum;
import com.bxbro.sun.common.base.enums.NoticeTypeEnum;
import com.bxbro.sun.common.base.enums.TaskStatusEnum;
import com.bxbro.sun.common.base.exception.SunException;
import com.bxbro.sun.common.tools.utils.DateUtils;
import com.bxbro.sun.platform.config.MailConfig;
import com.bxbro.sun.platform.config.ShortMessageConfig;
import com.bxbro.sun.platform.mapper.TaskManageMapper;
import com.bxbro.sun.platform.service.feign.NoticeFeign;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
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
    private MailConfig mailConfig;
    @Resource
    private ShortMessageConfig shortMessageConfig;

    @Value("${sun.notice-type}")
    private String noticeType;


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
                CommonMessageDTO commonMessageDTO = buildCommonMessageDTO(noticeType, task.getTaskName(), task.getContent());
                noticeFeign.sendMessage(commonMessageDTO);

            } else if(StringUtils.compare(currentDateStr, deadlineStr) > 0) {
                XxlJobHelper.log("The task [{}] has been delayed.", task.getTaskName());
                // 将task设置为已超期
                task.setTaskStatus(TaskStatusEnum.DELAYED.getCode());
                taskManageMapper.updateTask(task);
            } else {
                // do nothing
            }
        }
        XxlJobHelper.log("=======MailScheduleTask execute end. ========");
    }


    /**
     * 根据通知类型构建消息体
     * @param noticeType
     * @param taskName
     * @param taskContent
     * @return
     */
    private CommonMessageDTO buildCommonMessageDTO(String noticeType, String taskName, String taskContent) {

        if (CharSequenceUtil.isEmpty(noticeType) || CharSequenceUtil.isEmpty(taskName)
                || CharSequenceUtil.isEmpty(taskContent)) {
            return null;
        }
        CommonMessageDTO commonMessageDTO = null;
        if (NoticeTypeEnum.MAIL.getDesc().equals(noticeType)) {
            commonMessageDTO = new MailDTO(taskName, taskContent, mailConfig.getFromAddress(), mailConfig.getFromAddress());
        } else if (NoticeTypeEnum.SHORT_MESSAGE.getDesc().equals(noticeType)) {
            commonMessageDTO = new ShortMessageDTO(shortMessageConfig.getKey(), shortMessageConfig.getSecret());
        } else {
            throw new SunException(BusinessEnum.UNKNOWN_NOTICE_TYPE);
        }
        commonMessageDTO.setNoticeType(noticeType);
        return commonMessageDTO;
    }
}
