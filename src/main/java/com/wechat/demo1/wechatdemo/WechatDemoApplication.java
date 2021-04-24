package com.wechat.demo1.wechatdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@MapperScan("com.wechat.demo1.wechatdemo.mapper")
//@EnableGlobalMethodSecurity(securedEnabled=true)
@EnableAsync
@EnableScheduling
public class WechatDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(WechatDemoApplication.class, args);
    }

}
