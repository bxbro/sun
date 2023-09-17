package com.bxbro.sun.notice.task;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.text.CharSequenceUtil;
import com.bxbro.sun.common.base.domain.dto.CommonMessageDTO;
import com.bxbro.sun.common.base.domain.dto.MailDTO;
import com.bxbro.sun.common.base.domain.dto.ShortMessageDTO;
import com.bxbro.sun.common.base.enums.BusinessEnum;
import com.bxbro.sun.common.base.enums.DateTypeEnum;
import com.bxbro.sun.common.base.enums.NoticeTypeEnum;
import com.bxbro.sun.common.base.exception.SunException;
import com.bxbro.sun.common.tools.utils.DateUtils;
import com.bxbro.sun.common.tools.utils.ListUtils;
import com.bxbro.sun.notice.config.ShortMessageConfig;
import com.bxbro.sun.notice.domain.dto.MemorialDayDTO;
import com.bxbro.sun.notice.domain.dto.MemorialDayUserDTO;
import com.bxbro.sun.notice.service.IMemorialDayService;
import com.bxbro.sun.notice.service.IMemorialDayUserRelService;
import com.bxbro.sun.notice.support.MessageSupport;
import com.bxbro.sun.notice.support.MessageSupportFactory;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

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
@RequiredArgsConstructor
public class MemorialDayScheduleTask {

    private final IMemorialDayService memorialDayService;

    private final IMemorialDayUserRelService memorialDayUserRelService;

    private final MessageSupportFactory messageSupportFactory;

    private final ShortMessageConfig shortMessageConfig;

    @Value("${sun.notice-type}")
    private String noticeType;


    @XxlJob(value = "noticeMemorialDayHandler")
    public void noticeMemorialDayHandler() {
        XxlJobHelper.log(">>>>>> noticeMemorialDayHandler start >>>>>>>");

        // 1.遍历t_memorial_day表，获取所有需要提醒的纪念日
        List<MemorialDayDTO> memorialDayDTOList = memorialDayService.listAll();
        if (CollUtil.isEmpty(memorialDayDTOList)) {
            XxlJobHelper.log(">>>>>>数据库中不存在需要提醒的纪念日>>>>>>>");
            return;
        }
        for (MemorialDayDTO dto : memorialDayDTOList) {
            if (CharSequenceUtil.isEmpty(dto.getMemorialDayDate())
                    || CharSequenceUtil.isEmpty(dto.getNoticeTimePoints())) {
                continue;
            }
            // 将日期统一转成阳历
            Date date = unifyDate(dto);
            String currentDateStr = DateUtils.format(new Date(), DateUtils.DATE_PATTERN);
            Date currentDate = DateUtils.stringToDate(currentDateStr, DateUtils.DATE_PATTERN);
            // 计算当天日期与纪念日日期的差值
            Long diffValue = DateUtils.calcDiffValue(currentDate, date);

            String[] noticeTimePointArray = dto.getNoticeTimePoints().split(",");
            List<Long> noticeFreqList = ListUtils.convertStr2Long(noticeTimePointArray);
            if (noticeFreqList.contains(diffValue)) {
                List<MemorialDayUserDTO> dtoList = memorialDayUserRelService.listUsersByDayId(dto.getId());
                dtoList.forEach(e -> {
                    String content = buildEmailContent(dto, diffValue, e);
                    MessageSupport messageSupport = messageSupportFactory.getMessageSupportByNoticeType(noticeType);
                    messageSupport.sendMessage(buildCommonMessageDTO(e, content));
                });
            }
        }
        XxlJobHelper.log(">>>>>> noticeMemorialDayHandler end >>>>>>>");
    }

    /**
     * 构建消息体
     * @param e
     * @param content
     * @return
     */
    private CommonMessageDTO buildCommonMessageDTO(MemorialDayUserDTO e, String content) {
        CommonMessageDTO commonMessageDTO = null;
        if (NoticeTypeEnum.MAIL.getDesc().equals(noticeType)) {
            commonMessageDTO = new MailDTO("纪念日提醒", content, e.getEmail(), "1756330108@qq.com");
        } else if (NoticeTypeEnum.SHORT_MESSAGE.getDesc().equals(noticeType)) {
            commonMessageDTO = new ShortMessageDTO(shortMessageConfig.getKey(), shortMessageConfig.getSecret());
        } else {
            throw new SunException(BusinessEnum.UNKNOWN_NOTICE_TYPE);
        }
        return commonMessageDTO;
    }


    /**
     * 统一日期类型
     * @param dto
     * @return
     */
    private Date unifyDate(MemorialDayDTO dto) {
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
        return date;
    }


    /**
     * 组装邮件内容
     * @param dto
     * @param diffValue
     * @param e
     * @return
     */
    private String buildEmailContent(MemorialDayDTO dto, Long diffValue, MemorialDayUserDTO e) {
        StringBuilder builder = new StringBuilder();
        builder.append("尊敬的").append(e.getNickName()).append("先生/女士:");
        builder.append("\n");
        builder.append("\n距离").append("【").append(dto.getMemorialDayName())
                .append(dto.getMemorialDayDate()).append("】").append("还有").append(Math.abs(diffValue)).append("天；");
        builder.append("\n记得提前为Ta准备惊喜噢~~");
        builder.append("\n\n");
        builder.append("\n您的贴心管家Sun System持续为您服务！");
        return builder.toString();
    }


}
