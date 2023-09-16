package com.bxbro.sun.notice.support;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 消息接口工厂
 *
 * @author: dong
 * @date: 2023/9/16 17:06
 * @since: 1.0
 */
@Component
public class MessageSupportFactory {

    @Resource
    private ApplicationContext ctx;

    public <T> T getMessageSupport(Class<T> clazz) {
        return ctx.getBean(clazz);
    }
}
