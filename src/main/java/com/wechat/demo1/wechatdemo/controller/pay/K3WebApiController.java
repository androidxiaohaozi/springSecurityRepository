package com.wechat.demo1.wechatdemo.controller.pay;

import com.wechat.demo1.wechatdemo.service.pay.K3WebApiService;
import com.wechat.demo1.wechatdemo.utils.ResultBean;
import com.wechat.demo1.wechatdemo.vo.pay.DonListVo;
import com.wechat.demo1.wechatdemo.vo.pay.InvoiceAppVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description TODO
 * @Auther Administrator
 * @Date 2021/4/23 15:50
 */
@RestController
@RequestMapping("/K3WebApi")
public class K3WebApiController {

    @Autowired
    private K3WebApiService k3WebApiService;

    /**
     * 获取捐赠明细接口数据
     * @param donListVo donlistVo
     * @return ResultBean
     */
    @RequestMapping(value = "/getDonationDetails", method = RequestMethod.POST)
    public ResultBean getDonationDetails(@RequestBody DonListVo donListVo) {
        return k3WebApiService.getDonationDetails(donListVo);
    }


    /**
     * 传送开票申请
     * @param invoiceAppVo invoiceAppVo
     * @return ResultBean
     */
    @RequestMapping(value = "/getInvoiceApplication", method = RequestMethod.POST)
    public ResultBean getInvoiceApplication(@RequestBody InvoiceAppVo invoiceAppVo) {
        return k3WebApiService.getInvoiceApplication(invoiceAppVo);
    }

}
