package com.bxbro.sun.notice.service.impl;

import com.bxbro.sun.notice.domain.form.MailForm;
import com.bxbro.sun.notice.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void send(MailForm mailForm) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject(mailForm.getSubject());
        simpleMailMessage.setText(mailForm.getContent());
        simpleMailMessage.setFrom(mailForm.getFromAddress());
        simpleMailMessage.setTo(mailForm.getToAddress());
        javaMailSender.send(simpleMailMessage);
    }
}
