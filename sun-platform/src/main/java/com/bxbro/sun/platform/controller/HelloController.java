package com.bxbro.sun.platform.controller;

import com.bxbro.sun.common.base.domain.dto.MailDTO;
import com.bxbro.sun.common.tools.utils.ResultUtil;
import com.bxbro.sun.core.model.BaseResult;
import com.bxbro.sun.platform.config.MailConfig;
import com.bxbro.sun.platform.service.feign.NoticeFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class HelloController {

    @Autowired
    NoticeFeign noticeFeign;
    @Autowired
    MailConfig mailConfig;

    @GetMapping("/hello")
    public String sayHello() {
        String fromAddress = mailConfig.getFromAddress();
        return "The sun is rising." + fromAddress;
    }

    @PostMapping("sendMail")
    public BaseResult sendMail() {
        MailDTO mailDto = new MailDTO();
        mailDto.setContent(">>>>just for test>>>>");
        mailDto.setSubject(">>>>测试>>>>");
        mailDto.setFromAddress("1756330108@qq.com");
        mailDto.setToAddress("1756330108@qq.com");
        mailDto.setNoticeType("short-message");
        noticeFeign.sendMessage(mailDto);
        return ResultUtil.outSuccess();
    }
}
