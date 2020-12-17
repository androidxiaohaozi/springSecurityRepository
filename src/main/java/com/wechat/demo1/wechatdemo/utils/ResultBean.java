package com.wechat.demo1.wechatdemo.utils;

import java.io.Serializable;

/** @author  */
public class ResultBean implements Serializable {

  /** 序列化标识 */
  private static final long serialVersionUID = 1L;

  public static final int SUCCESS = 0;

  public static final int FAIL = -1;

  public static final String SUCCESS_MESSAGE_CODE = "success";

  public static final String FAIL_MESSAGE_CODE = "fail";

  public static final String SUCCESS_MESSAGE_DESCRIPTION = "成功";

  public static final String FAIL_MESSAGE_DESCRIPTION = "失败";

  /** 返回码，全局默认成功码为0，失败为-1 */
  private int code;

  /** 返回信息描述code */
  private String message_code;

  /** 返回信息描述description */
  private String message_description;

  /** 返回对象，可以是简单对象，封装处理后的对象，或者MAP */
  private Object object;

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public String getMessage_code() {
    return message_code;
  }

  public void setMessage_code(String message_code) {
    this.message_code = message_code;
  }

  public String getMessage_description() {
    return message_description;
  }

  public void setMessage_description(String message_description) {
    this.message_description = message_description;
  }

  public Object getObject() {
    return object;
  }

  public void setObject(Object object) {
    this.object = object;
  }

  public ResultBean() {}

  public static ResultBean result(int code) {
    ResultBean result = new ResultBean();
    if (SUCCESS == code) {
      result.setCode(SUCCESS);
      result.setMessage_code(SUCCESS_MESSAGE_CODE);
      result.setMessage_description(SUCCESS_MESSAGE_DESCRIPTION);
    } else {
      result.setCode(FAIL);
      result.setMessage_code(FAIL_MESSAGE_CODE);
      result.setMessage_description(FAIL_MESSAGE_DESCRIPTION);
    }
    return result;
  }

  public static ResultBean result(int code, String message_code, String message_description) {
    ResultBean result = new ResultBean();
    if (SUCCESS == code) {
      result.setCode(SUCCESS);
    } else {
      result.setCode(FAIL);
    }
    result.setMessage_code(message_code);
    result.setMessage_description(message_description);
    return result;
  }

  public static ResultBean result(int code, Object object) {
    ResultBean result = new ResultBean();
    if (SUCCESS == code) {
      result.setCode(SUCCESS);
      result.setMessage_code(SUCCESS_MESSAGE_CODE);
      result.setMessage_description(SUCCESS_MESSAGE_DESCRIPTION);
    } else {
      result.setCode(FAIL);
      result.setMessage_code(FAIL_MESSAGE_CODE);
      result.setMessage_description(FAIL_MESSAGE_DESCRIPTION);
    }
    result.setObject(object);
    return result;
  }

  @Override
  public String toString() {
    return "ResultBean{"
        + "code="
        + code
        + ", message_code='"
        + message_code
        + '\''
        + ", message_description='"
        + message_description
        + '\''
        + ", object="
        + object
        + '}';
  }
}
