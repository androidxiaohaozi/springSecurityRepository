package com.wechat.demo1.wechatdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@MapperScan("com.wechat.demo1.wechatdemo.mapper")
@EnableGlobalMethodSecurity(securedEnabled=true)
public class WechatDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(WechatDemoApplication.class, args);
    }

}
