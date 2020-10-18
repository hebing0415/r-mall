package com.robot.order.service.impl;

import com.robot.api.response.Message;
import com.robot.order.service.PayService;
import org.springframework.stereotype.Service;

@Service
public class PayServiceImpl implements PayService {


    @Override
    public Message toPay() throws Exception {
        return Message.success();
    }

}
