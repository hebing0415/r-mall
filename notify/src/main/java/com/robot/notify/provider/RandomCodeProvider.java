package com.robot.notify.provider;

public interface RandomCodeProvider {


    boolean createCode(String str);
    /**
     * 验证二维码
     * @param str
     * @return
     */
    boolean verify(String str,String input);
}
