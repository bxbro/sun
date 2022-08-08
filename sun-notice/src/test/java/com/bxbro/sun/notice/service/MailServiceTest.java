package com.bxbro.sun.notice.service;

import com.bxbro.sun.notice.domain.form.MailForm;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Description: TODO
 * @Author: dong
 * @Date 2022/8/8 22:41
 * @Since 1.0
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class MailServiceTest {

    @Autowired
    MailService mailService;

    @Test
    public void testSendMail() {
        MailForm mailForm = new MailForm();
        mailForm.setContent(">>>>just for test>>>>");
        mailForm.setSubject(">>>>测试>>>>");
        mailForm.setFromAddress("1756330108@qq.com");
        mailForm.setToAddress("1015177471@qq.com");
        mailService.send(mailForm);
        log.info("=======send succeed!=========");
    }
}
