package com.wechat.demo1.wechatdemo.utils;

import java.util.concurrent.ConcurrentHashMap;

public class AccessToken extends ConcurrentHashMap<String, Object> {
    /**
     *AccessToken
     */
    private static final long serialVersionUID = 1L;

    private static class LazyHolder {
        private static final AccessToken INSTANCE = new AccessToken();
    }
    private AccessToken (){}
    public static final AccessToken getInstance() {
        return LazyHolder.INSTANCE;
    }
}
