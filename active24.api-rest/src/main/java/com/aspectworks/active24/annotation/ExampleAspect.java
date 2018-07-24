package com.aspectworks.active24.annotation;

import com.aspectworks.active24.api.rest.TopicController;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class ExampleAspect {
    final static Logger logger = org.slf4j.LoggerFactory.getLogger(ExampleAspect.class);
    private long counter = 0L;

    @Before("@annotation(MaxRequestPerUserCount)")
    public void procede(JoinPoint call) {
        logger.info("procede called");
        MethodSignature signature = (MethodSignature) call.getSignature();
        Method method = signature.getMethod();

        MaxRequestPerUserCount maxRequestPerUserCount = method.getAnnotation(MaxRequestPerUserCount.class);
        final long requestLimit = maxRequestPerUserCount.value();


        logger.info("request from method " + String.valueOf(counter));

        if (counter > requestLimit) {
            throw new RequestLimitExceededException();
        }
        counter++;
    }
}
