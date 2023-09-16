package com.bxbro.sun.notice.controller;

import com.bxbro.sun.common.base.domain.dto.CommonMessageDTO;
import com.bxbro.sun.common.base.enums.BusinessEnum;
import com.bxbro.sun.common.base.enums.NoticeTypeEnum;
import com.bxbro.sun.common.base.exception.SunException;
import com.bxbro.sun.common.tools.utils.ResultUtil;
import com.bxbro.sun.core.model.BaseResult;
import com.bxbro.sun.notice.support.MailSupport;
import com.bxbro.sun.notice.support.MessageSupport;
import com.bxbro.sun.notice.support.ShortMessageSupport;
import com.bxbro.sun.notice.support.MessageSupportFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 主通知服务控制器
 * @Author: dong
 * @Date 2022/8/7 18:06
 * @Since 1.0
 */
@RestController
@RequestMapping("/notice/api")
@RequiredArgsConstructor
public class MainNoticeController {

    private final MessageSupportFactory messageSupportFactory;


    @PostMapping("/v1")
    public BaseResult<Void> sendMessage(@RequestBody CommonMessageDTO commonMessageDTO) {
        MessageSupport messageSupport = getMessageSupport(commonMessageDTO.getNoticeType());
        messageSupport.sendMessage(commonMessageDTO);
        return ResultUtil.outSuccess();
    }


    private MessageSupport getMessageSupport(String noticeType) {
        MessageSupport messageSupport = null;
        if(NoticeTypeEnum.MAIL.getDesc().equals(noticeType)) {
            messageSupport = messageSupportFactory.getMessageSupport(MailSupport.class);
        } else if (NoticeTypeEnum.SHORT_MESSAGE.getDesc().equals(noticeType)) {
            messageSupport = messageSupportFactory.getMessageSupport(ShortMessageSupport.class);
        } else {
            throw new SunException(BusinessEnum.UNKNOWN_NOTICE_TYPE);
        }
        return messageSupport;
    }
}
