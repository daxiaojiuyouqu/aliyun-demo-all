package com.aliyun.aliyundemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.aliyun.aliyundemo.dao")
public class AliyunDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(AliyunDemoApplication.class, args);
    }

}
