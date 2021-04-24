package com.wechat.demo1.wechatdemo.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description K3ResultBean
 * @Auther Administrator
 * @Date 2021/4/23 15:34
 */
@Data
public class K3ResultBean implements Serializable {

    /**
     * 1-处理成功，0-处理失败
     */
    private int code;

    /**
     * 处理成功
     */
    private String content;

    /**
     * 处理失败
     */
    private String message;

    /**
     * 返回信息
     */
    private Object object;
}
