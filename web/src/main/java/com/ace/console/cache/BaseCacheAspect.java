package com.ace.console.cache;

import com.ace.console.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.util.StringUtils;

/**
 * @Project_Name: ace
 * @File: BaseCacheAspect
 * (C) Copyright ACE Corporation 2013 All Rights Reserved.
 * @Author: denghp
 * @Date: 14-11-2
 * @Time: 下午2:41
 * @Description:
 */
public class BaseCacheAspect implements InitializingBean {


    protected final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private CacheManager ssmCacheManager;

    private Cache cache;

    protected String cacheName;

    /**
     * 缓存池名称
     *
     * @param cacheName
     */
    public void setCacheName(String cacheName) {

        this.cacheName = cacheName;
    }

    /**
     * 缓存管理器
     *
     * @return
     */
    public void setCacheManager(CacheManager cacheManager) {
        this.ssmCacheManager = cacheManager;
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        cache = ssmCacheManager.getCache(cacheName);
    }


    public void clear() {
        LOGGER.info("cacheName:{}, cache clear", cacheName);
        this.cache.clear();
    }

    public void evict(String key) {
        LOGGER.info("cacheName:{}, evict key:{}", cacheName, key);
        this.cache.evict(key);
    }

    public <T> T get(Object key) {
        LOGGER.info("cacheName:{}, get key:{}", cacheName, key);
        if (StringUtils.isEmpty(key)) {
            return null;
        }
        Cache.ValueWrapper value = cache.get(key);
        if (value == null) {
            return null;
        }
        return (T) value.get();
    }

    public void put(String key, Object value) {
        LOGGER.info("cacheName:{}, put key:{}", cacheName, key);
        this.cache.put(key, value);
    }

    /**
     * 获取KEY的生成
     * @param key 主键KEY 必须
     * @param namespace   命名空间,DEFAULT = df_namespace
     * @return
     */
    public String getCacheKey(String key, Class namespace) {
        if (StringUtils.isEmpty(key)) {
            LOGGER.error("The key is empty.");
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder(Constants.DEFAULT_PROJECT_NAME).append(":");
        if (StringUtils.isEmpty(namespace)) {
            stringBuilder.append(Constants.DEFAULT_NAMESPACE).append(":");
        } else {
            stringBuilder.append(namespace.getSimpleName()).append(":");
        }
        stringBuilder.append(key);
        return stringBuilder.toString();
    }
}
