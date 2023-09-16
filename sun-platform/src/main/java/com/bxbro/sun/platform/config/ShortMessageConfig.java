package com.bxbro.sun.platform.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * TODO
 *
 * @author: dong
 * @date: 2023/9/16 19:58
 * @since: 1.0
 */
@ConfigurationProperties(prefix = "sun.short-message")
@Component
@Data
public class ShortMessageConfig {

    private String key;

    private String secret;
}
