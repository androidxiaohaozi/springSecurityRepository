package com.wechat.demo1.wechatdemo.config;

import io.netty.channel.ConnectTimeoutException;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.protocol.HttpContext;
import org.apache.log4j.Logger;

import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

/**
 * @Description TODO
 * @Auther Administrator
 * @Date 2021/4/24 9:53
 */
public class MyHttpRequestRetryHandler implements HttpRequestRetryHandler {

    private static Logger log = Logger.getLogger(MyHttpRequestRetryHandler.class);

    @Override
    public boolean retryRequest(IOException e, int i, HttpContext httpContext) {
        if (i > 3) {
            log.info("连接第" + i + "次");
            return false;
        }

        //没有相应，重试
        if (e instanceof NoHttpResponseException) {
            return true;
        }
        //链接超时，重试
        if (e instanceof ConnectTimeoutException) {
            return true;
        }
        //链接或读取超时，重试
        if (e instanceof SocketTimeoutException) {
            return true;
        }
        //本地证书异常
        if (e instanceof SSLHandshakeException) {
            return false;
        }
        //被中断
        if (e instanceof InterruptedIOException) {
            return false;
        }
        //找不到服务
        if (e instanceof UnknownHostException) {
            return false;
        }
        //SSL握手异常
        if (e instanceof SSLException) {
            return false;
        }

        HttpClientContext clientContext = HttpClientContext.adapt(httpContext);

        HttpRequest httpRequest = clientContext.getRequest();

        boolean idempotent = !(httpRequest instanceof HttpEntityEnclosingRequest);
        //如果请求是幂等的，就再次尝试
        return idempotent;
    }
}
