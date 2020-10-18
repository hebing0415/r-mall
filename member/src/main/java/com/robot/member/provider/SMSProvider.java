package com.robot.member.provider;

import com.robot.api.response.Message;

public interface SMSProvider {


    Message sendEmail(String phone,String address);

    Message sendSMS(String phone);
}
