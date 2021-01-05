package com.wechat.demo1.wechatdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.wechat.demo1.wechatdemo.mapper")
public class WechatDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(WechatDemoApplication.class, args);
    }

}
