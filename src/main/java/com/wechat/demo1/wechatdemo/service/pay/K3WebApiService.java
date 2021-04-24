package com.wechat.demo1.wechatdemo.service.pay;

import com.wechat.demo1.wechatdemo.utils.ResultBean;
import com.wechat.demo1.wechatdemo.vo.pay.DonListVo;
import com.wechat.demo1.wechatdemo.vo.pay.InvoiceAppVo;

public interface K3WebApiService {

    ResultBean getDonationDetails(DonListVo donListVo);

    ResultBean getInvoiceApplication(InvoiceAppVo invoiceAppVo);
}
