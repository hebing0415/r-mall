package com.robot.home.aspect;

import com.alibaba.fastjson.JSONObject;
import com.robot.home.annotation.ControllerLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@Aspect
@Component
@Slf4j
public class LogAspect {


    @Pointcut("@annotation(com.robot.home.annotation.ControllerLog)")
    public void controllerAspect() {
    }

    @Around("controllerAspect()")
    public Object before(ProceedingJoinPoint joinPoint) throws Throwable {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Class targetClass = Class.forName(targetName);
        Object[] arguments = joinPoint.getArgs();
        Method[] methods = targetClass.getMethods();
        String description = "";
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazz = method.getParameterTypes();
                if (clazz.length == arguments.length) {
                    description = method.getAnnotation(ControllerLog.class).description();
                    break;
                }
            }
        }
        //请求参数
        List<Object> argsList = new ArrayList<>();
        for (int i = 0; i < arguments.length; i++) {
            // 如果参数类型是请求和响应的http，则不需要拼接【这两个参数，使用JSON.toJSONString()转换会抛异常】
            if (arguments[i] instanceof HttpServletRequest || arguments[i] instanceof HttpServletResponse) {
                continue;
            }
            argsList.add(arguments[i]);
        }
        String uid = "123456";
        log.info("用户:{},操作:{},入参:{}", uid, description, JSONObject.toJSONString(argsList));
        long startTime = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        log.info("用户:{},操作:{},入参:{},结果:{},耗时:{}", uid, description, JSONObject.toJSONString(argsList),
                JSONObject.toJSONString(proceed), (System.currentTimeMillis() - startTime) + "ms");
        return proceed;
    }
}
