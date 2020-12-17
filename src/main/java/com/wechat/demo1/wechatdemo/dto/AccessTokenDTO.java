package com.wechat.demo1.wechatdemo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @Description TODO
 * @Auther Administrator
 * @Date 2020/12/17 8:30
 */
@Data
public class AccessTokenDTO {

    private Integer errcode;

    private String errmsg;

    @JsonProperty(value = "access_token")
    private String accessToken;

    @JsonProperty(value = "expires_in")
    private String expiresIn;
}
