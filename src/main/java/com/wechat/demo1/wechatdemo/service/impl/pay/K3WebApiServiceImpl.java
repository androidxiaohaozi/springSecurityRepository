package com.wechat.demo1.wechatdemo.service.impl.pay;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wechat.demo1.wechatdemo.common.K3ResultBean;
import com.wechat.demo1.wechatdemo.service.pay.K3WebApiService;
import com.wechat.demo1.wechatdemo.utils.AesEncryptUtil;
import com.wechat.demo1.wechatdemo.utils.ResultBean;
import com.wechat.demo1.wechatdemo.vo.pay.DonListVo;
import com.wechat.demo1.wechatdemo.vo.pay.InvoiceAppVo;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;

/**
 * @Description TODO
 * @Auther Administrator
 * @Date 2021/4/23 15:21
 */
@Service
@Transactional
public class K3WebApiServiceImpl implements K3WebApiService {

    @Value("${SyncGetDonListURL}")
    private String SyncGetDonListURL;

    @Value("${SyncGetInvoiceURL}")
    private String SyncGetInvoiceURL;

    @Autowired
    private RequestConfig requestConfig;

    @Autowired
    private CloseableHttpClient httpClient;

    private static final Logger log = LoggerFactory.getLogger(K3WebApiServiceImpl.class);
    @Override
    public ResultBean getDonationDetails(DonListVo donListVo) {

        long timestamp = System.currentTimeMillis();

        String sign = DigestUtils.md5DigestAsHex((AesEncryptUtil.K3WebApiGetDonList + timestamp +
                AesEncryptUtil.K3WebApiEndSign).getBytes());

        K3ResultBean k3ResultBean;

        ResultBean resultBean = new ResultBean();

        donListVo.setTimestamp(timestamp);
        donListVo.setSign(sign);

        if (donListVo.getWayCode() == null || "".equals(donListVo.getWayCode())) {
            return ResultBean.result(ResultBean.FAIL, "捐赠渠道编码必录");
        }

        if (donListVo.getDonName() == null || "".equals(donListVo.getDonName())) {
            return ResultBean.result(ResultBean.FAIL, "捐赠人名称必录");
        }

        if (donListVo.getDonDate() == null || "".equals(donListVo.getDonDate())) {
            return ResultBean.result(ResultBean.FAIL, "捐赠日期为空");
        }

        if (donListVo.getDonAmount() == null) {
            return ResultBean.result(ResultBean.FAIL, "捐赠金额不许为空");
        }

        try {
            String jsonMessage = JSONObject.toJSON(donListVo).toString();
            log.info("项目新增参数" + jsonMessage);

            k3ResultBean = doPost(SyncGetDonListURL, jsonMessage);

        } catch (Exception e) {
            resultBean.setCode(-1);
            resultBean.setObject("K3接口请求失败");
            log.info("请求返回结果" + "Exception错误，请求失败");
            return resultBean;
        }

        if (k3ResultBean.getCode() ==  0) {

            resultBean.setCode(-1);
            resultBean.setObject(k3ResultBean.getMessage());

            log.info("请求返回结果" + k3ResultBean.getMessage());
            return resultBean;
            //请求成功
        } else {

            resultBean.setCode(0);
            resultBean.setObject(k3ResultBean.getContent());

            log.info("请求返回结果" + k3ResultBean.getContent());
            return resultBean;
        }
    }

    @Override
    public ResultBean getInvoiceApplication(InvoiceAppVo invoiceAppVo) {

        long timestamp = System.currentTimeMillis();

        String sign = DigestUtils.md5DigestAsHex((AesEncryptUtil.K3WebApiInvoice + timestamp +
                AesEncryptUtil.K3WebApiEndSign).getBytes());

        K3ResultBean k3ResultBean;

        ResultBean resultBean = new ResultBean();

        if (invoiceAppVo.getDate() == null || "".equals(invoiceAppVo.getDate())) {
            return ResultBean.result(ResultBean.FAIL, "申请开票日期为空");
        }

        if (invoiceAppVo.getRecipient() == null || "".equals(invoiceAppVo.getRecipient())) {
            return ResultBean.result(ResultBean.FAIL, "收件人为空");
        }

        if (invoiceAppVo.getTel() == null || "".equals(invoiceAppVo.getTel())) {
            return ResultBean.result(ResultBean.FAIL, "联系电话为空");
        }

        if (invoiceAppVo.getTitle() == null || "".equals(invoiceAppVo.getTitle())) {
            return ResultBean.result(ResultBean.FAIL, "票据抬头为空");
        }

        invoiceAppVo.setTimestamp(timestamp);
        invoiceAppVo.setSign(sign);

        try {
            String jsonMessage = JSONObject.toJSON(invoiceAppVo).toString();
            log.info("项目新增参数" + jsonMessage);

            k3ResultBean = doPost(SyncGetInvoiceURL, jsonMessage);

        } catch (Exception e) {
            resultBean.setCode(-1);
            resultBean.setObject("K3接口请求失败");
            log.info("请求返回结果" + "Exception错误，请求失败");
            return resultBean;
        }

        if (k3ResultBean.getCode() ==  0) {

            resultBean.setCode(-1);
            resultBean.setObject(k3ResultBean.getMessage());

            log.info("请求返回结果" + k3ResultBean.getMessage());
            return resultBean;
            //请求成功
        } else {

            resultBean.setCode(0);
            resultBean.setObject(k3ResultBean.getContent());

            log.info("请求返回结果" + k3ResultBean.getContent());
            return resultBean;
        }
    }

    public K3ResultBean doPost(String url, String jsonMessage) throws Exception {
        HttpPost httpPost = new HttpPost(url);

        httpPost.setConfig(requestConfig);

        if (jsonMessage != null) {
            StringEntity entity = new StringEntity(jsonMessage, "UTF-8"); // 解决中文乱码问题
            entity.setContentEncoding("UTF-8");
            entity.setContentType("application/json");
            httpPost.setEntity(entity);
        }

        CloseableHttpResponse response = this.httpClient.execute(httpPost);

        String returnEntity = EntityUtils.toString(response.getEntity(), "UTF-8");
        return JSON.parseObject(returnEntity, K3ResultBean.class);
    }

}
