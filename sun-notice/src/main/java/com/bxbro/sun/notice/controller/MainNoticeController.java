package com.bxbro.sun.notice.controller;

import com.bxbro.sun.common.base.domain.dto.CommonMessageDTO;
import com.bxbro.sun.common.tools.utils.ResultUtil;
import com.bxbro.sun.core.model.BaseResult;
import com.bxbro.sun.notice.support.MessageSupport;
import com.bxbro.sun.notice.support.MessageSupportFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 主通知服务控制器
 * @Author: dong
 * @Date 2022/8/7 18:06
 * @Since 1.0
 */
@RestController
@RequestMapping("/notice/api")
@RequiredArgsConstructor
public class MainNoticeController {

    private final MessageSupportFactory messageSupportFactory;


    @PostMapping("/v1")
    public BaseResult<Void> sendMessage(@RequestBody CommonMessageDTO commonMessageDTO) {
        MessageSupport messageSupport =
                messageSupportFactory.getMessageSupportByNoticeType(commonMessageDTO.getNoticeType());
        messageSupport.sendMessage(commonMessageDTO);
        return ResultUtil.outSuccess();
    }
}
