package com.robot.notify.rest;


import com.robot.api.response.Message;
import com.robot.notify.service.AliYunSMSService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/notify")
public class NotifyRest {

    @Resource
    private AliYunSMSService aliYunSMSService;

    @RequestMapping("/sendMail")
    @ResponseBody
    private Message sendMail() {

        return Message.success();
    }
}

