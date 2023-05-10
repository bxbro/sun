package com.bxbro.sun.notice.service.impl;

import com.alibaba.fastjson.JSON;
import com.bxbro.sun.common.base.domain.dto.MailDto;
import com.bxbro.sun.notice.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description: 邮件通知Service类
 * @Author: dong
 * @Date 2022/8/7 18:19
 * @Since 1.0
 */
@Service
public class MailServiceImpl implements MailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MailServiceImpl.class);

    @Resource
    JavaMailSenderImpl javaMailSender;

    @Override
    public void send(MailDto mailDto) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject(mailDto.getSubject());
        simpleMailMessage.setText(mailDto.getContent());
        simpleMailMessage.setFrom(mailDto.getFromAddress());
        simpleMailMessage.setTo(mailDto.getToAddress());
        LOGGER.info("the content of mail is [{}]", JSON.toJSONString(simpleMailMessage));
        javaMailSender.send(simpleMailMessage);
        LOGGER.info("Send mail succeed.");
    }
}
