package com.wechat.demo1.wechatdemo.po;

import java.io.Serializable;
import java.util.Date;

/**
 * approval_token_data
 * @author 
 */
public class ApprovalTokenData implements Serializable {
    private String sp_no;

    private String sp_name;

    private Date apply_time;

    private String user_id;

    private Double new_money;

    private String cost_type;

    private Integer sp_status;

    private Long id;

    private Integer valid;

    private Date create_time;

    private String creater;

    private Date update_time;

    private String updater;

    private static final long serialVersionUID = 1L;

    public String getSp_no() {
        return sp_no;
    }

    public void setSp_no(String sp_no) {
        this.sp_no = sp_no;
    }

    public String getSp_name() {
        return sp_name;
    }

    public void setSp_name(String sp_name) {
        this.sp_name = sp_name;
    }

    public Date getApply_time() {
        return apply_time;
    }

    public void setApply_time(Date apply_time) {
        this.apply_time = apply_time;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public Double getNew_money() {
        return new_money;
    }

    public void setNew_money(Double new_money) {
        this.new_money = new_money;
    }

    public String getCost_type() {
        return cost_type;
    }

    public void setCost_type(String cost_type) {
        this.cost_type = cost_type;
    }

    public Integer getSp_status() {
        return sp_status;
    }

    public void setSp_status(Integer sp_status) {
        this.sp_status = sp_status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getValid() {
        return valid;
    }

    public void setValid(Integer valid) {
        this.valid = valid;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }
}