package com.robot.member;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

/**
 * @author robot
 * @date 2019/12/09
 */
@PropertySource(value ="classpath:member.yml" )
@SpringBootApplication(scanBasePackages = {"com.robot.api","com.robot.member"})
@EnableDubbo(scanBasePackages = "com.robot.member.provider")
@MapperScan(value="com.robot.member.dao")
public class MemberApplication {

    private static Logger logger = LoggerFactory.getLogger(MemberApplication.class);

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        SpringApplication.run(MemberApplication.class, args);
        long endTime = System.currentTimeMillis();
        logger.info("----- MemberApplication start  use time :{}", endTime - startTime);

    }

}
