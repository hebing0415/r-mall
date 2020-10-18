//package com.robot.home.config;
//
//import com.robot.home.interceptor.JwtInterceptor;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
//
//import javax.annotation.Resource;
//
//@Configuration
//public class ApiConfig extends WebMvcConfigurationSupport {
//
//    @Resource
//    private JwtInterceptor jwtInterceptor;
//
//    @Override
//    protected void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(this.jwtInterceptor).addPathPatterns("/**")
//                .excludePathPatterns("/home/userLogin");
//        super.addInterceptors(registry);
//    }
//
//}
