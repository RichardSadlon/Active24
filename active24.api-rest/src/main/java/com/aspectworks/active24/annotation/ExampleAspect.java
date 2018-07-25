package com.aspectworks.active24.annotation;

import com.aspectworks.active24.api.rest.RequestCounter;
import com.aspectworks.active24.api.rest.TopicController;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.Configuration;
import org.ehcache.xml.XmlConfiguration;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

import static org.ehcache.config.builders.CacheManagerBuilder.newCacheManager;

@Aspect
@Component
public class ExampleAspect {
    final static Logger logger = org.slf4j.LoggerFactory.getLogger(ExampleAspect.class);
    Integer counter;

    @Autowired
    Cache<Long, RequestCounter> cache;
    RequestCounter requestCounter;

    @Before("@annotation(MaxRequestPerUserCount)")
    public void procede(JoinPoint call) {
//        CacheConfig cs = CacheConfig.getINSTANCE();
//        cache = cs.getCache();
        if (cache.get(12345L) == null) {
            cache.put(12345L, new RequestCounter());
        }
        requestCounter = cache.get(12345L);
        MethodSignature signature = (MethodSignature) call.getSignature();
        Method method = signature.getMethod();
        if (requestCounter.getCurrentMethodCountMap().get(method.getName()) == null) {
            requestCounter.addToCurrentMethodCountMap(method.getName(), 0);
        }
        counter = requestCounter.getCurrentMethodCountMap().get(method.getName());
        MaxRequestPerUserCount maxRequestPerUserCount = method.getAnnotation(MaxRequestPerUserCount.class);
        final long requestLimit = maxRequestPerUserCount.value();
        if (counter > requestLimit) {
            throw new RequestLimitExceededException();
        }
        requestCounter.addToCurrentMethodCountMap(method.getName(), ++counter);
        cache.put(12345L, requestCounter);
    }
}
