package com.bxbro.sun.common.domain.dto;

import lombok.Data;

/**
 * @Description: TODO
 * @Author: dong
 * @Date 2022/8/7 18:20
 * @Since 1.0
 */
@Data
public class MailDto {
    /**-----邮件主题------**/
    private String subject;

    /**-----邮件内容------**/
    private String content;

    /**-----邮件送达者地址------**/
    private String toAddress;

    /**-----邮件发送者地址------**/
    private String fromAddress;
}
