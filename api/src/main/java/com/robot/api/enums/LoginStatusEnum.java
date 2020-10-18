package com.robot.api.enums;

/**
 * @author robot
 * @date 2020/2/12 11:04
 */
public enum LoginStatusEnum {
    LOGIN_IN(1, "已登录"),
    LOGIN_OUT(2, "已退出");

    LoginStatusEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private int code;
    private String msg;

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
