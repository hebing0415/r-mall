package com.robot.notify.provider;


import com.robot.api.pojo.AliYunEmail;

public interface AliYunSMSProvider {

    boolean sendEmail(AliYunEmail aliYunEmail, String accessKey, String accessSecret);

}
