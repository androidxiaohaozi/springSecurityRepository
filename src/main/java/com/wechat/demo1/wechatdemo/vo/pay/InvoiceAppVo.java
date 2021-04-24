package com.wechat.demo1.wechatdemo.vo.pay;

import lombok.Data;

/**
 * @Description TODO
 * @Auther Administrator
 * @Date 2021/4/23 15:57
 */
@Data
public class InvoiceAppVo {

    /**
     * 时间戳
     */
    private Long timestamp;

    /**
     * 方法签名
     */
    private String sign;

    /**
     * 申请开票日期
     */
    private String date;

    /**
     * 收件人
     */
    private String recipient;

    /**
     * 联系电话
     */
    private String tel;

    /**
     * 邮寄地址
     */
    private String address;

    /**
     * 电子邮箱
     */
    private String email;

    /**
     * 是否企业 1：企业，0：个人
     */
    private String isCom;

    /**
     * 票据抬头
     */
    private String title;

    /**
     * 企业社会信用代码
     */
    private String creditCode;

    /**
     * 备注
     */
    private String remark;

    /**
     * 捐赠明细
     */
    private InvoiceDonlistVo invoiceDonlistVo;
}
