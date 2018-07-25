//package com.aspectworks.active24.api.rest;
//
//
//import com.aspectworks.active24.annotation.ExampleAspect;
//import org.ehcache.Cache;
//import org.ehcache.CacheManager;
//import org.ehcache.config.Configuration;
//import org.ehcache.xml.XmlConfiguration;
//import org.slf4j.Logger;
//import org.springframework.stereotype.Component;
//
//import static org.ehcache.config.builders.CacheManagerBuilder.newCacheManager;
//
//@Component
//public class CacheConfig {
//    final static Logger logger = org.slf4j.LoggerFactory.getLogger(CacheConfig.class);
//    private Cache<Long, RequestCounter> cache;
//    private static final CacheConfig INSTANCE = new CacheConfig();
//
//    private CacheConfig() {
//        Configuration xmlConfig = new XmlConfiguration(CacheConfig.class.getResource("/cache/ehcache.xml"));
//        CacheManager cacheManager = newCacheManager(xmlConfig);
//        cacheManager.init();
//        cache = cacheManager.getCache("requests", Long.class, RequestCounter.class);
//    }
//
//    public Cache<Long, RequestCounter> getCache() {
//        return cache;
//    }
//
//    public static CacheConfig getINSTANCE() {
//        return INSTANCE;
//    }
//}
