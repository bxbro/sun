package com.bxbro.sun.notice.controller;

import com.bxbro.sun.common.domain.BaseResult;
import com.bxbro.sun.common.utils.ResultUtil;
import com.bxbro.sun.common.domain.dto.MailDto;
import com.bxbro.sun.notice.service.MailService;
import org.springframework.web.bind.annotation.*;

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
