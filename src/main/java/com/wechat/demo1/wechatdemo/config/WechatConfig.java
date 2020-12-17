package com.wechat.demo1.wechatdemo.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @Description TODO
 * @Auther Administrator
 * @Date 2020/12/17 9:29
 */
@Component
@Getter
@Setter
@Configuration
@ConfigurationProperties(value = "wechat")
@PropertySource("classpath:application.yml")
public class WechatConfig {

    private String agentId;

    private String secret;

    private String accessTokenUrl;

    private String spNoListUrl;

    private String dataUrl;
}
