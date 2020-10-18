package com.robot.order;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author robot
 * @date 2019/12/09
 */
@SpringBootApplication(scanBasePackages ={ "com.robot.api","com.robot.order"},exclude={DataSourceAutoConfiguration.class})
@EnableDubbo(scanBasePackages = "com.robot.order.provider")
@MapperScan(value="com.robot.order.dao")
@PropertySource("classpath:dubbo.properties")
//@NacosPropertySource(dataId = "order-config", autoRefreshed = true)
public class OrderApplication {

    private static final Logger logger = LoggerFactory.getLogger(OrderApplication.class);

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        SpringApplication.run(OrderApplication.class, args);
        long endTime = System.currentTimeMillis();
        logger.info("----- OrderApplication start  use time :{}", endTime - startTime);
    }

}
