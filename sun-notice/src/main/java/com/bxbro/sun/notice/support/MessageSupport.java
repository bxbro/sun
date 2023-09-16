package com.bxbro.sun.notice.support;

import com.bxbro.sun.common.base.domain.dto.CommonMessageDTO;

/**
 * 所有消息的总接口
 *
 * @author: dong
 * @date: 2023/9/16 16:15
 * @since: 1.0
 */
public interface MessageSupport {

    /**
     * 发送消息
     */
    <T extends CommonMessageDTO> void sendMessage(T t);
}
