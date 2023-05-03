package com.bxbro.sun.gateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * @Description: 网关服务启动类
 * @Author: dong
 * @Date 2022/8/12 7:39
 * @Since 1.0
 */
@SpringCloudApplication
public class GatewayApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(GatewayApplication.class);

    public static void main(String[] args) {
        LOGGER.info(">>>>>>>>sun-gateway is starting >>>>>>");
        SpringApplication.run(GatewayApplication.class, args);
        LOGGER.info(">>>>>>>>sun-gateway started >>>>>>>");
    }
}
