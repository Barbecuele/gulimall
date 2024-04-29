package com.mxj.common.exception;

/**
 * @Title: BizCodeEnum
 * @Author mi xiu jin
 * @Package com.mxj.common.exception
 * @Date 2024/4/30 2:25
 * @description: 错误码
 */
public enum BizCodeEnum {
    UNKNOW_EXCEPTION(10000, "系统未知异常"),
    VALID_EXCEPTION(10001, "参数格式校验失败"),
    PRODUCT_UP_EXCEPTION(11000, "商品上架异常"),
    USER_EXIST_EXCEPTION(15001, "用户已存在"),
    PHONE_EXIST_EXCEPTION(15002, "手机号已存在"),
    EMAIL_EXIST_EXCEPTION(15003, "邮箱已存在"),
    USER_PASSWORD_EXCEPTION(15004, "密码错误");

    private int code;
    private String msg;

    BizCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static String getMsg(int code) {
        for (BizCodeEnum value : BizCodeEnum.values()) {
            if (value.getCode() == code) {
                return value.getMsg();
            }
        }
        return null;
    }
}
