package com.bxbro.sun.notice.service;

import com.bxbro.sun.notice.domain.form.MailForm;

public interface MailService {
    /**
     * 发送邮件
     * @param mailForm
     */
    void send(MailForm mailForm);
}
