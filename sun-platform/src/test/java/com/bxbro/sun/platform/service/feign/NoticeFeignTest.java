package com.bxbro.sun.platform.service.feign;

import com.bxbro.sun.common.base.domain.dto.MailDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Description: TODO
 * @Author: dong
 * @Date 2022/8/9 18:33
 * @Since 1.0
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class NoticeFeignTest {
    @Autowired
    NoticeFeign noticeFeign;

    @Test
    public void testSendMail() {
        MailDTO mailDto = new MailDTO();
        mailDto.setContent(">>>>just for test>>>>");
        mailDto.setSubject(">>>>测试>>>>");
        mailDto.setFromAddress("1756330108@qq.com");
        mailDto.setToAddress("1756330108@qq.com");
        noticeFeign.sendMessage(mailDto);
        log.info("=======send succeed!=========");
    }
}
