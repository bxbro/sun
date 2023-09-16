package com.bxbro.sun.notice.service;

import com.bxbro.sun.common.base.domain.dto.MailDTO;
import com.bxbro.sun.notice.support.MailSupport;
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
    MailSupport mailSupport;

    @Test
    public void testSendMail() {
        MailDTO mailDto = new MailDTO();
        mailDto.setContent(">>>>just for test>>>>");
        mailDto.setSubject(">>>>测试>>>>");
        mailDto.setFromAddress("1756330108@qq.com");
        mailDto.setToAddress("1015177471@qq.com");
        mailSupport.sendMessage(mailDto);
        log.info("=======send succeed!=========");
    }
}
