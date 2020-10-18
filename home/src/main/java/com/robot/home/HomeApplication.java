package com.robot.home;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author robot
 * @date 2019/12/26
 */
@SpringBootApplication(scanBasePackages = "com.robot.home",exclude={DataSourceAutoConfiguration.class})
@NacosPropertySource(dataId = "notify-config", autoRefreshed = true)
public class HomeApplication {

    private static Logger logger = LoggerFactory.getLogger(HomeApplication.class);

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        SpringApplication.run(HomeApplication.class, args);
        long endTime = System.currentTimeMillis();
        logger.info("---------- HomeApplication 启动成功  耗时 :{} ms ----------", endTime - startTime);
    }

}
