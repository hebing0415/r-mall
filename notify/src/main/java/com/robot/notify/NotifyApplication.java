package com.robot.notify;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo(scanBasePackages = "com.robot.notify.provider")
@MapperScan(value = "com.robot.notify.dao")
@NacosPropertySource(dataId = "notify-config", autoRefreshed = true)
public class NotifyApplication {

    private static Logger logger = LoggerFactory.getLogger(NotifyApplication.class);


    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        SpringApplication.run(NotifyApplication.class, args);
        long endTime = System.currentTimeMillis();
        logger.info("----- NotifyApplication start  use time :{} ms------------", endTime - startTime);
    }

}
