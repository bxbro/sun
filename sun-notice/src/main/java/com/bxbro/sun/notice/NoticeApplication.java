package com.bxbro.sun.notice;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

@SpringCloudApplication
@MapperScan(basePackages = "com.bxbro.sun.notice.mapper")
public class NoticeApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(NoticeApplication.class);

    public static void main(String[] args) {
        LOGGER.info(">>>>>>>>sun-notice is starting >>>>>>");
        SpringApplication.run(NoticeApplication.class, args);
        LOGGER.info(">>>>>>>>sun-notice started >>>>>>>");
    }
}
