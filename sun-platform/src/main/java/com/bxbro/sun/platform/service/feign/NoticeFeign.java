package com.bxbro.sun.platform.service.feign;

import com.bxbro.sun.common.domain.dto.MailDto;
import com.bxbro.sun.common.domain.dto.ResultDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "sun-notice")
@Component
public interface NoticeFeign {
    /**
     * 发送邮件
     * @param mailDto
     * @return
     */
    @PostMapping("/sun/mail/api/v1/new")
    ResultDto sendMail(@RequestBody MailDto mailDto);
}
