package com.robot.api.enums;

/**
 * 注册来源
 */
public enum RegisterSourceEnum {
    PHONE(1, "手机号注册"),
    EMAIL(2, "邮件注册");
    private int code;
    private String msg;

    RegisterSourceEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
