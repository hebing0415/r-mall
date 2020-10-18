package com.robot.notify.provider.impl;

import com.robot.api.pojo.AliYunEmail;
import com.robot.notify.provider.AliYunSMSProvider;
import com.robot.notify.service.AliYunSMSService;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;

@Service(version = "1.0.0", timeout = 5000)
public class AliYunSMSProviderImpl implements AliYunSMSProvider {

    @Resource
    private AliYunSMSService aliYunSMSService;

   public boolean sendEmail(AliYunEmail aliYunEmail, String accessKey, String accessSecret){
        return aliYunSMSService.sendEmail(aliYunEmail);
    }
}
