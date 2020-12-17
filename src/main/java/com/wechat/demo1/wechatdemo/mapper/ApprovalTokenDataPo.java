package com.wechat.demo1.wechatdemo.mapper;

import com.wechat.demo1.wechatdemo.po.ApprovalTokenData;

public interface ApprovalTokenDataPo {
    int deleteByPrimaryKey(String sp_no);

    int insert(ApprovalTokenData record);

    int insertSelective(ApprovalTokenData record);

    ApprovalTokenData selectByPrimaryKey(String sp_no);

    int updateByPrimaryKeySelective(ApprovalTokenData record);

    int updateByPrimaryKey(ApprovalTokenData record);
}