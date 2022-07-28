package com.bxbro.sun.platform;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

@SpringCloudApplication
@MapperScan(basePackages = "com.bxbro.sun.platform.mapper")
public class PlatFormApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlatFormApplication.class, args);
    }
}
