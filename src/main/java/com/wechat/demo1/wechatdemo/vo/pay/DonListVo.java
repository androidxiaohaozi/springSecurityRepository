package com.wechat.demo1.wechatdemo.vo.pay;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Description donlistvo
 * @Auther Administrator
 * @Date 2021/4/23 15:27
 */
@Data
public class DonListVo {

    /**
     * 时间戳
     */
    private Long timestamp;

    /**
     * 方法签名
     */
    private String sign;

    /**
     * 捐赠渠道编码
     */
    private String wayCode;

    /**
     * 捐赠人姓名
     */
    private String donName;

    /**
     * 捐赠开始日期
     */
    private String startDate;

    /**
     * 捐赠结束日期
     */
    private String endDate;

    /**
     * 捐赠金额
     */
    private BigDecimal donAmount;

    /**
     * 银行账号4位
     */
    private String bankAccNo;

    /**
     * 交易流水号后4位
     */
    private String transNo;
}
