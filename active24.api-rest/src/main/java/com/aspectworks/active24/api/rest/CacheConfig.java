package com.aspectworks.active24.api.rest;


import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.Configuration;
import org.ehcache.xml.XmlConfiguration;

import static org.ehcache.config.builders.CacheManagerBuilder.newCacheManager;

public class CacheConfig {
    private static Cache<Long, RequestCounter> cache;

    public static Cache<Long, RequestCounter> getCache() {
        Configuration xmlConfig = new XmlConfiguration(CacheConfig.class.getResource("/cache/ehcache.xml"));
        try (CacheManager cacheManager = newCacheManager(xmlConfig)) {
            cacheManager.init();
            cache = cacheManager.getCache("requests", Long.class, RequestCounter.class);
            return cache;
        }

    }

    public static RequestCounter loadCache(Long userId) {
        if (cache.get(userId) == null) {
            cache.put(userId, new RequestCounter());
        }
        return cache.get(userId);

    }


}
