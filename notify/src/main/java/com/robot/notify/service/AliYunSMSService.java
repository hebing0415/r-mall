package com.robot.notify.service;

import com.robot.api.pojo.AliYunEmail;

/**
 * @author robot
 * @date 2020/2/16 23:07
 */
public interface AliYunSMSService {

    boolean sendEmail(AliYunEmail aliYunEmail);
}
