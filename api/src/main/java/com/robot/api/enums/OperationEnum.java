package com.robot.api.enums;

public enum OperationEnum {
    LOGIN("1", "手机号注册"),
    EMAIL("2", "邮件注册");
    private String code;
    private String msg;

    OperationEnum(String code, String msg) {
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

    public static boolean judge(String type) {
        for (OperationEnum operationEnum : OperationEnum.values()) {

        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(judge("2"));
    }
}
