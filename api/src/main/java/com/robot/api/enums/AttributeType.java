package com.robot.api.enums;

public enum AttributeType {

    ONE(1,"tree"),
    TWO(2,"properties");
    private Integer code;
    private String msg;


    AttributeType(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
