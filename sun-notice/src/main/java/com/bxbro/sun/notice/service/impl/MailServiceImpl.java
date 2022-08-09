package com.bxbro.sun.notice.service.impl;

import com.bxbro.sun.common.domain.dto.MailDto;
import com.bxbro.sun.notice.service.MailService;
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

    @Resource
    JavaMailSenderImpl javaMailSender;

    @Override
    public void send(MailDto mailDto) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject(mailDto.getSubject());
        simpleMailMessage.setText(mailDto.getContent());
        simpleMailMessage.setFrom(mailDto.getFromAddress());
        simpleMailMessage.setTo(mailDto.getToAddress());
        javaMailSender.send(simpleMailMessage);
    }
}
