package com.robot.order.producer;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BaseProducer {

//    @Resource
//    private RocketMQTemplate rocketMQTemplate;

//    @Resource
//    private DefaultMQProducer defaultMQProducer;

    public void sendOrderMsg(String topic, Object msg) throws Exception{
        log.info("发送订单消息,topic:{},msg:{}", topic, JSONObject.toJSONString(msg));
//        rocketMQTemplate.convertAndSend(topic, msg);
        log.info("发送订单消息结束");
    }

}
