package com.robot.api.enums;

public enum NxxxEnum {
    /**
     * 不存在就..
     */
    nx("NX"),
    /**
     * 存在就..
     */
    ex("EX");

    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    NxxxEnum(String key) {
        this.key = key;
    }
}
