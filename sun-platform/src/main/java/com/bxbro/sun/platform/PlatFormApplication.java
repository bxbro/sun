package com.bxbro.sun.platform;


import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

@SpringCloudApplication
@MapperScan(basePackages = "com.bxbro.sun.platform.mapper")
public class PlatFormApplication {

    private static final Logger logger = LoggerFactory.getLogger(PlatFormApplication.class);

    public static void main(String[] args) {
        logger.info(">>>>>>>>Sun System is starting.>>>>>>");
        SpringApplication.run(PlatFormApplication.class, args);
        logger.info(">>>>>>>>Sun System started.>>>>>>>");
    }
}
