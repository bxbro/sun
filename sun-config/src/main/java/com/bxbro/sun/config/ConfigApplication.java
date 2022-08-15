package com.bxbro.sun.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * 配置中心启动类
 */
@SpringCloudApplication
@EnableConfigServer
public class ConfigApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigApplication.class);

    public static void main(String[] args) {
        LOGGER.info(">>>>>>>>sun-config is starting >>>>>>");
        SpringApplication.run(ConfigApplication.class, args);
        LOGGER.info(">>>>>>>>sun-config started >>>>>>>");
    }
}
