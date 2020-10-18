package com.robot.order.job;

import com.robot.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 订单定时任务 ，这里我们采用spring 自带的定时任务
 * Quartz 或者 elastic-job 这两种定时任务更适合 分布式系统
 */
@Slf4j
@Component
@EnableScheduling
public class OrderJob {


    @Resource
    private OrderService orderService;


    @Scheduled(cron = "${orderJob.cron}")
    public void execute() {
        log.info("OrderJob deleteOrder start");
        orderService.deleteOrder();
        log.info("OrderJob deleteOrder end");
    }
}
