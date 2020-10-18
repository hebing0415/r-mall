package com.robot.order.consumer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.robot.api.enums.OrderStatusEnum;
import com.robot.api.pojo.OrderAndLine;
import com.robot.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
//@RocketMQMessageListener(consumerGroup = "consumerGroup", topic = "orderTopic")
public class OrderConsumer implements RocketMQListener<JSONObject> {

    @Resource
    private OrderService orderService;

    @Override
    public void onMessage(JSONObject msg) {
        Integer status = OrderStatusEnum.TO_BE_PAID.getCode();
        try {
            log.info("topic:orderTopic, group:consumerGroup 接收到消息:{}", msg);
            OrderAndLine orderAndLine = JSON.parseObject(msg.toJSONString(), new TypeReference<OrderAndLine>() {
            });
            orderService.insertOrder(orderAndLine, status);
            log.info("topic:orderTopic, group:consumerGroup 消费完成!");
        } catch (Exception e) {
            log.info("topic:orderTopic, group:consumerGroup 消费失败:{}", msg, e);
        }
    }
}
