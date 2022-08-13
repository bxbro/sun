package com.bxbro.sun.platform;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringCloudApplication
@MapperScan(basePackages = "com.bxbro.sun.platform.mapper")
@EnableFeignClients(basePackages = "com.bxbro.sun.platform.service.feign")
@EnableScheduling
public class PlatFormApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(PlatFormApplication.class);

    public static void main(String[] args) {
        LOGGER.info(">>>>>>>>sun-platform is starting.>>>>>>");
        SpringApplication.run(PlatFormApplication.class, args);
        LOGGER.info(">>>>>>>>sun-platform started.>>>>>>>");
    }
}
