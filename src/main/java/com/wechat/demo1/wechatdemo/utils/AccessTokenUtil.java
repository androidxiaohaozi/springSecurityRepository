package com.wechat.demo1.wechatdemo.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;

@Component
public class AccessTokenUtil {

    //测试企业
    private  String corpid = "ww164a24f6c9e8898b";
    private  String corpsecret = "5bOcNXZpht28psx1OxBLbJZv7PHAXmAhi7w5zfOENwE";
    //正式企业
//    private  String corpid = "ww39d3aed060e69472";
//    private  String corpsecret = "xxQhBYAUiOuj2MJqePl-hgP9IPd8geQMDIJ7QwjAwnA";

    private static final String URL = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=";

    /**
     * Token工具
     *
     * @return
     * @throws Exception
     */
    public  String getAccessToken() throws Exception {
        // 首先判断AccessToken是否已经获取并在有效期内如果是直接返回当前AccessToken否则重新请求

        AccessToken session = AccessToken.getInstance();
        if (session.get("access_token") != null && session.get("creatTime") != null) {
            Long creatTime = Long.valueOf(session.get("creatTime").toString());
            Long nowTime = (new Date()).getTime();
            if (nowTime - creatTime < 7200000) {
                // System.out.println("AccessToken来自缓存");
                return (String) session.get("access_token");
            }
        }
        // 初始化httpclient
        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 使用get请求
        HttpGet httpget = new HttpGet(URL+corpid+"&corpsecret="+corpsecret);
        // 发送请求
        HttpResponse response = null;
        HttpEntity entity = null;
        String resultStr = null;
        Object accessToken = null;
        try {
            response = httpclient.execute(httpget);
            // 获得响应
            entity = response.getEntity();
            resultStr = EntityUtils.toString(entity, "utf-8");
            System.out.println(resultStr);
            JSONObject json = JSONObject.parseObject(resultStr);
            accessToken = json.get("access_token");
            EntityUtils.consume(entity);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (httpget != null) {
                httpget.releaseConnection();
            }
        }
        session.put("access_token", accessToken.toString());
        session.put("creatTime", (new Date()).getTime());
        //System.out.println("AccessToken来新获取");
        return accessToken.toString();
    }
}
