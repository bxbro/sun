package com.bxbro.sun.notice.service;

import com.bxbro.sun.common.base.domain.dto.MailDto;

public interface MailService {
    /**
     * 发送邮件
     * @param mailDto
     */
    void send(MailDto mailDto);
}
