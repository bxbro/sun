package com.bxbro.sun.notice.controller;

import com.bxbro.sun.common.base.domain.dto.MailDto;
import com.bxbro.sun.common.tools.utils.ResultUtil;
import com.bxbro.sun.core.model.BaseResult;
import com.bxbro.sun.notice.service.MailService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description: 邮件通知Controller
 * @Author: dong
 * @Date 2022/8/7 18:06
 * @Since 1.0
 */
@RestController
@RequestMapping("/mail/api")
public class MailController {

    @Resource
    private MailService mailService;

    @PostMapping("/v1/new")
    public BaseResult sendMail(@RequestBody MailDto mailDto) {
        mailService.send(mailDto);
        return ResultUtil.outSuccess();
    }
}
