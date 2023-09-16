package com.bxbro.sun.notice.support;

import com.bxbro.sun.common.base.domain.dto.CommonMessageDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 短信消息实现类
 *
 * @author: dong
 * @date: 2023/9/16 16:13
 * @since: 1.0
 */
@Component
@Slf4j
public class ShortMessageSupport implements MessageSupport {


    @Override
    public void sendMessage(CommonMessageDTO commonMessageDTO) {
        // TODO: 2023/9/16

        log.info("发送短信完成!");
    }
}
