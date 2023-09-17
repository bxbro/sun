package com.bxbro.sun.notice.support;

import com.bxbro.sun.common.base.enums.BusinessEnum;
import com.bxbro.sun.common.base.enums.NoticeTypeEnum;
import com.bxbro.sun.common.base.exception.SunException;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 消息接口工厂
 *
 * @author: dong
 * @date: 2023/9/16 17:06
 * @since: 1.0
 */
@Component
public class MessageSupportFactory {

    @Resource
    private ApplicationContext ctx;

    public <T> T getMessageSupport(Class<T> clazz) {
        return ctx.getBean(clazz);
    }


    /**
     * 根据通知类型获取MessageSupport
     * @param noticeType
     * @return
     */
    public MessageSupport getMessageSupportByNoticeType(String noticeType) {
        MessageSupport messageSupport = null;
        if(NoticeTypeEnum.MAIL.getDesc().equals(noticeType)) {
            messageSupport = getMessageSupport(MailSupport.class);
        } else if (NoticeTypeEnum.SHORT_MESSAGE.getDesc().equals(noticeType)) {
            messageSupport = getMessageSupport(ShortMessageSupport.class);
        } else {
            throw new SunException(BusinessEnum.UNKNOWN_NOTICE_TYPE);
        }
        return messageSupport;
    }
}
