package com.wechat.demo1.wechatdemo.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;

@Service
public class WxUserUtil {
    @Autowired
    private AccessTokenUtil accessTokenUtil;

    private String URL = "https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo?access_token=";

    private String URL2 = "https://qyapi.weixin.qq.com/cgi-bin/user/get?access_token=";

    private String URL3 = "https://qyapi.weixin.qq.com/cgi-bin/user/getuserid?access_token=";


    public ResultBean getUser(String code) throws Exception {

        // 初始化httpclient
        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 使用get请求
        HttpGet httpget = new HttpGet(URL+accessTokenUtil.getAccessToken()+"&code="+code);
        // 发送请求
        HttpResponse response = null;
        HttpEntity entity = null;
        String resultStr = null;
        String  res = null;
        WxUserPo wxUserPo = new WxUserPo();
        try {
            response = httpclient.execute(httpget);
            // 获得响应
            entity = response.getEntity();
            resultStr = EntityUtils.toString(entity, "utf-8");
            System.out.println(resultStr);
            JSONObject json = JSONObject.parseObject(resultStr);
            Object userId = json.get("UserId");
            if(null != userId){

                res = getUserReal(userId.toString());
                JSONObject object = JSONObject.parseObject(res);
                wxUserPo.setName(object.get("name").toString());
                wxUserPo.setPhone(object.get("mobile").toString());
                wxUserPo.setUserId(object.get("userid").toString());

            }
            EntityUtils.consume(entity);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (httpget != null) {
                httpget.releaseConnection();
            }
        }
        return ResultBean.result(ResultBean.SUCCESS,wxUserPo);
    }

    public ResultBean getMobile(String mobile) throws Exception {
        // 初始化httpclient
        CloseableHttpClient httpclient = HttpClients.createDefault();
        mobile="{mobile:"+mobile+"}";
        JSONObject jsonObject = JSONObject.parseObject(mobile);
        StringEntity stringEntity = new StringEntity(jsonObject.toString());//param参数，可以为"key1=value1&key2=value2"的一串字符串
        stringEntity.setContentType("application/x-www-form-urlencoded");
        // 使用post请求
        HttpPost httpPost = new HttpPost(URL3+accessTokenUtil.getAccessToken());
        httpPost.setEntity(stringEntity);
        HttpClient client = new DefaultHttpClient();
        HttpResponse httpResponse = client.execute(httpPost);
        // 发送请求
        HttpResponse response = null;
        HttpEntity entity = null;
        String resultStr = null;
        String  res = null;
        try {
            response = httpclient.execute(httpPost);
            // 获得响应
            entity = response.getEntity();
            resultStr = EntityUtils.toString(entity, "utf-8");
            System.out.println(resultStr);
            JSONObject json = JSONObject.parseObject(resultStr);
            Object userId = json.get("userid");
            if(null != userId){
                res = getUserReal(userId.toString());
            }
            EntityUtils.consume(entity);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (httpPost != null) {
                httpPost.releaseConnection();
            }
        }

        return ResultBean.result(ResultBean.SUCCESS,res);
    }

    private String getUserReal(String userid) throws Exception {

        // 初始化httpclient
        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 使用get请求
        HttpGet httpget = new HttpGet(URL2+accessTokenUtil.getAccessToken()+"&userid="+userid);
        // 发送请求
        HttpResponse response = null;
        HttpEntity entity = null;
        String resultStr = null;
        String  res = null;
        try {
            response = httpclient.execute(httpget);
            // 获得响应
            entity = response.getEntity();
            resultStr = EntityUtils.toString(entity, "utf-8");
            System.out.println(resultStr);
            JSONObject json = JSONObject.parseObject(resultStr);
            json.put("userid",userid);
            if(null != json){
                res = json.toString();
            }
            EntityUtils.consume(entity);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (httpget != null) {
                httpget.releaseConnection();
            }
        }

        return res;
    }

}
