package com.aliyun.aliyundemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@MapperScan("com.aliyun.aliyundemo.dao")
@EnableCaching
public class AliyunDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(AliyunDemoApplication.class, args);
    }

}
