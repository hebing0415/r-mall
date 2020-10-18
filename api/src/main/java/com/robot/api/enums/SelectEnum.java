package com.robot.api.enums;

/**
 * 全选反选
 */

public enum  SelectEnum {

    SELECT_ALL("1","全选"),
    ANTI_ELECTION("0","反选");
    private String code;
    private String msg;


    SelectEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
