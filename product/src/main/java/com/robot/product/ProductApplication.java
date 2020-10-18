package com.robot.product;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author robot
 * @date 2019/12/09
 */
@SpringBootApplication(scanBasePackages ={ "com.robot.api","com.robot.product"},exclude={DataSourceAutoConfiguration.class})
@EnableDubbo(scanBasePackages = "com.robot.product.provider")
@MapperScan(value="com.robot.product.dao")
public class ProductApplication {

    private static Logger logger = LoggerFactory.getLogger(ProductApplication.class);

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        SpringApplication.run(ProductApplication.class, args);
        long endTime = System.currentTimeMillis();
        logger.info("----- ProductApplication start  use time :{} ms----------", endTime - startTime);
    }

}
