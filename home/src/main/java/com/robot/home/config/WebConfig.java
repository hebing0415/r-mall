package com.robot.home.config;

import com.robot.home.interceptor.JwtInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author robot
 * @date 2020/1/8 11:29
 */

@Configuration
public class WebConfig implements WebMvcConfigurer {


    /**
     * 添加拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JwtInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/home/userLogin")
                .excludePathPatterns("/home/pwdLogin")
                .excludePathPatterns("/home/getCode")
                .excludePathPatterns("/home/carouselList")
                .excludePathPatterns("/home/productList")
                .excludePathPatterns("/order/wxPayNotify");
    }

}
