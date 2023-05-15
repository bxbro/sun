package com.bxbro.sun.notice.support;

import com.alibaba.fastjson.JSON;
import com.bxbro.sun.common.base.domain.dto.MailDto;
import com.bxbro.sun.common.base.exception.SunException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 邮件工具类
 *
 * @author: dong
 * @date: 2023/5/11 18:46
 * @since: 1.0
 */
@Component
public class MailHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(MailHelper.class);

    @Resource
    JavaMailSenderImpl javaMailSender;

    /**
     * 发送邮件
     * @param mailDto
     */
    public void sendMail(MailDto mailDto) {
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
