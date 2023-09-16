package com.bxbro.sun.notice.support;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSON;
import com.bxbro.sun.common.base.domain.dto.CommonMessageDTO;
import com.bxbro.sun.common.base.domain.dto.MailDTO;
import com.bxbro.sun.common.base.exception.SunException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 邮件消息服务类
 *
 * @author: dong
 * @date: 2023/5/11 18:46
 * @since: 1.0
 */
@Component
public class MailSupport implements MessageSupport {

    private static final Logger LOGGER = LoggerFactory.getLogger(MailSupport.class);

    @Resource
    JavaMailSenderImpl javaMailSender;

    /**
     * 发送邮件
     * @param commonMessageDTO
     */
    @Override
    public void sendMessage(CommonMessageDTO commonMessageDTO) {
        MailDTO mailDto = BeanUtil.copyProperties(commonMessageDTO, MailDTO.class);
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject(mailDto.getSubject());
        simpleMailMessage.setText(mailDto.getContent());
        simpleMailMessage.setFrom(mailDto.getFromAddress());
        simpleMailMessage.setTo(mailDto.getToAddress());
        LOGGER.info("the content of mail is [{}]", JSON.toJSONString(simpleMailMessage));
        try {
            javaMailSender.send(simpleMailMessage);
        } catch (SunException ex) {
            LOGGER.error(String.format("Exception happened at [%s]", ex));
        }
        LOGGER.info("Send mail succeed.");
    }
}
