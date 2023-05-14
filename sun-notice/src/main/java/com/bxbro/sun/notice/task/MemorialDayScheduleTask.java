package com.bxbro.sun.notice.task;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.text.CharSequenceUtil;
import com.bxbro.sun.common.base.domain.dto.MailDto;
import com.bxbro.sun.common.base.enums.DateTypeEnum;
import com.bxbro.sun.common.tools.utils.DateUtils;
import com.bxbro.sun.common.tools.utils.ListUtils;
import com.bxbro.sun.notice.domain.dto.MemorialDayDto;
import com.bxbro.sun.notice.domain.dto.MemorialDayUserDto;
import com.bxbro.sun.notice.service.IMemorialDayService;
import com.bxbro.sun.notice.service.IMemorialDayUserRelService;
import com.bxbro.sun.notice.support.MailHelper;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 纪念日提醒 定时任务
 *
 * @author: dong
 * @date: 2023/5/11 22:30
 * @since: 1.0
 */
@Component
@EnableScheduling
public class MemorialDayScheduleTask {

    @Resource
    private IMemorialDayService memorialDayService;
    @Resource
    private IMemorialDayUserRelService memorialDayUserRelService;
    @Resource
    private MailHelper mailHelper;


//    @XxlJob(value = "noticeMemorialDayHandler")
    @Scheduled(cron = "* 0/1 * * * ?")
    public void noticeMemorialDayHandler() {
        XxlJobHelper.log(">>>>>> noticeMemorialDayHandler start >>>>>>>");

        // 1.遍历t_memorial_day表，获取所有需要提醒的纪念日
        List<MemorialDayDto> memorialDayDtoList = memorialDayService.listAll();
        if (CollUtil.isEmpty(memorialDayDtoList)) {
            XxlJobHelper.log(">>>>>>数据库中不存在需要提醒的纪念日>>>>>>>");
            return;
        }
        for (MemorialDayDto dto : memorialDayDtoList) {
            if (CharSequenceUtil.isEmpty(dto.getMemorialDayDate())
                    || CharSequenceUtil.isEmpty(dto.getNoticeTimePoints())) {
                continue;
            }

            Date date = null;
            String memorialDayDate = dto.getMemorialDayDate();
            // 如果是农历，需要转换一下
            if (DateTypeEnum.LUNAR_CALENDAR == dto.getDateType()) {
                String month = memorialDayDate.split("-")[0];
                String day = memorialDayDate.split("-")[1];
                date = DateUtils.lunar2solar(Integer.valueOf(month), Integer.valueOf(day));
            } else {
                // 获取今年年份
                int currentYear = Calendar.getInstance().get(Calendar.YEAR);
                String dateStr = currentYear + "-" + memorialDayDate;
                date = DateUtils.stringToDate(dateStr, DateUtils.DATE_PATTERN);
            }
            String currentDateStr = DateUtils.format(new Date(), DateUtils.DATE_PATTERN);
            Date currentDate = DateUtils.stringToDate(currentDateStr, DateUtils.DATE_PATTERN);
            Long diffValue = DateUtils.calcDiffValue(currentDate, date);
            String[] noticeTimePointArray = dto.getNoticeTimePoints().split(",");
            List<Long> noticeList = ListUtils.convertStr2Long(noticeTimePointArray);
            if (noticeList.contains(diffValue)) {
                List<MemorialDayUserDto> dtoList = memorialDayUserRelService.listUsersByDayId(dto.getId());
                dtoList.forEach(e->{
                    StringBuilder builder = buildEmailContent(dto, diffValue, e);
                    mailHelper.sendMail(new MailDto("纪念日提醒", builder.toString(), e.getEmail(), "1756330108@qq.com"));
                });
            }
        }
        XxlJobHelper.log(">>>>>> noticeMemorialDayHandler end >>>>>>>");
    }


    private StringBuilder buildEmailContent(MemorialDayDto dto, Long diffValue, MemorialDayUserDto e) {
        StringBuilder builder = new StringBuilder();
        builder.append("尊敬的 ").append(e.getUserName()).append(" 先生/女士:");
        builder.append("\n距离").append("【").append(dto.getMemorialDayName()).append("】").append("还有").append(Math.abs(diffValue)).append("天；");
        builder.append("\n记得提前为Ta准备惊喜噢~");
        builder.append("\n");
        builder.append("\n您的贴心管家Sun System持续为您服务！");
        return builder;
    }


}
