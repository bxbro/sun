package com.bxbro.sun.platform.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Description: 邮件相关配置
 * @Author: dong
 * @Date 2022/8/12 22:53
 * @Since 1.0
 */
@ConfigurationProperties(prefix = "sun.mail")
@Component
@Data
public class MailConfig {
    /**
     * cron表达式
     */
    private String cron;
    /**
     * 目标地址
     */
    private String toAddress;
    /**
     * 源地址
     */
    private String fromAddress;
    /**
     * 生日祝福
     */
    private String birthdayText;
}
