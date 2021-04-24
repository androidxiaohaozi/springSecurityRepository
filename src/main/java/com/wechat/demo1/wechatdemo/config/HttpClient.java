package com.wechat.demo1.wechatdemo.config;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description TODO
 * @Auther Administrator
 * @Date 2021/4/24 9:52
 */
@Configuration
public class HttpClient {

    @Value("${spring.http-pool.max-total}")
    private Integer maxTotal;

    @Value("${spring.http-pool.default-max-per-route}")
    private Integer defaultMaxPerRoute;

    @Value("${spring.http-pool.connect-timeout}")
    private Integer connectTimeout;

    @Value("${spring.http-pool.connection-request-timeout}")
    private Integer connectionRequestTimeout;

    @Value("${spring.http-pool.socket-timeout}")
    private Integer socketTimeout;

    @Value("${spring.http-pool.stale-Connection-CheckEnabled}")
    private boolean staleConnectionCheckEnabled;


    /**
     * 首先初始化一个连接池管理器，设置最大连接数，并发连接数
     * @return PoolingHttpClientConnectionManager
     */
    @Bean(name = "httpClientConnectionManager")
    public PoolingHttpClientConnectionManager getHttpClientConnectionManager() {

        PoolingHttpClientConnectionManager httpClientConnectionManager = new PoolingHttpClientConnectionManager();

        //最大连接数
        httpClientConnectionManager.setMaxTotal(maxTotal);
        //并发数
        httpClientConnectionManager.setDefaultMaxPerRoute(defaultMaxPerRoute);

        return httpClientConnectionManager;
    }


    /**
     * 实例化连接池，设置连接池管理器
     * 这里需要以参数形式注入上面实例化的连接池管理器
     * @param poolingHttpClientConnectionManager poolingHttpClientConnectionManager
     * @return HttpClientBuilder
     */
    @Bean(name = "httpClientBuilder")
    public HttpClientBuilder getHttpClientBuilder(
            @Qualifier("httpClientConnectionManager")PoolingHttpClientConnectionManager
                    poolingHttpClientConnectionManager) {
        //构造方法被protected修饰，所以只能用create
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();

        httpClientBuilder.setConnectionManager(poolingHttpClientConnectionManager);

        return httpClientBuilder;
    }

    /**
     * 注入连接池，用于获取httpClient,自定义重试策略
     * @param httpClientBuilder  httpClientBuilder
     * @return CloseableHttpClient
     */
    @Bean
    public CloseableHttpClient getCloseableHttpClient(
            @Qualifier("httpClientBuilder") HttpClientBuilder httpClientBuilder) {
        httpClientBuilder.setRetryHandler(new MyHttpRequestRetryHandler());
        return httpClientBuilder.build();
    }

    @Bean(name = "builder")
    public RequestConfig.Builder getBuilder() {
        RequestConfig.Builder builder = RequestConfig.custom();

        return builder.setConnectTimeout(connectTimeout)
                .setConnectionRequestTimeout(connectionRequestTimeout)
                .setSocketTimeout(socketTimeout);
//                .setStaleConnectionCheckEnabled(staleConnectionCheckEnabled);
    }

    /**
     * 使用builder构建一个RequestConfig对象
     * @param builder builder
     * @return requestConfig
     */
    @Bean
    public RequestConfig getRequestConfig(@Qualifier("builder") RequestConfig.Builder builder) {
        return builder.build();
    }
}
