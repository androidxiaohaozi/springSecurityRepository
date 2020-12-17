package com.wechat.demo1.wechatdemo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @Description TODO
 * @Auther Administrator
 * @Date 2020/12/17 11:21
 */
@Data
public class SpDataListDTO {

    private Integer errcode;

    private String errmsg;

    @JsonProperty(value = "sp_no_list")
    private List<String> spNoList;
}
