package com.robot.notify.service;

public interface RandomCodeService {


    String createCode(String str);
    /**
     * 验证验证码
     * @param str
     * @return
     */
    boolean verify(String str, String input);
}
