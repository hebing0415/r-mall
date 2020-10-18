package com.robot.api.enums;

public enum ExpxEnum {

    /**
     * 过期时间为秒
     */
    ex("EX"),
    /**
     * 过期时间为毫秒
     */
    px("NX");

    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    ExpxEnum(String key) {
        this.key = key;
    }
}
