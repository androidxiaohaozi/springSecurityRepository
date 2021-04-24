package com.wechat.demo1.wechatdemo.vo.pay;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description TODO
 * @Auther Administrator
 * @Date 2021/4/23 16:11
 */
@Data
public class InvoiceDonlistVo implements Serializable {

    /**
     * 捐赠日期
     */
    private String donDate;

    /**
     * 捐赠人姓名
     */
    private String donName;

    /**
     * 交易流水号
     */
    private String transNo;
}
