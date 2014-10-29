/**
 * Copyright (c) 2009-2015 http://demi-panda.com
 *
 * Licensed 
 */
package com.ace.console.cache;

import com.google.code.ssm.api.ParameterValueKeyProvider;
import com.google.code.ssm.api.ReadThroughSingleCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * @Project_Name: ace-web
 * @File: BaseCacheAspect
 * (C) Copyright ACE Corporation 2014 All Rights Reserved.
 * @Author: denghp
 * @Date: 10/15/14
 * @Time: 9:06 PM
 * @Description: 用于缓存的基础切面
 */
public class BaseCacheAspect implements InitializingBean {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private CacheManager cacheManager;

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
        this.cacheManager = cacheManager;
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        cache = cacheManager.getCache(cacheName);
    }

    /**
     * 清空缓存
     */
    public void clear() {
        log.info("cacheName:{}, cache clear", cacheName);
        this.cache.clear();
    }

    /**
     * 根据KEY移除相应的缓存
     * @param key
     */
    public void evict(String key) {
        log.info("cacheName:{}, evict key:{}", cacheName, key);
        this.cache.evict(key);
    }

    /**
     * 获取缓存的对象
     * @param key
     * @param <T>
     * @return
     */
    public <T> T get(Object key) {
        log.info("cacheName:{}, get key:{}", cacheName, key);
        if (StringUtils.isEmpty(key)) {
            return null;
        }
        Cache.ValueWrapper value = cache.get(key);
        if (value == null) {
            return null;
        }
        return (T) value.get();
    }

    /**
     * 添加缓存
     * @param key
     *        缓存的KEY
     * @param value
     *        缓存的值
     */
    public void put(@ParameterValueKeyProvider String key, Object value) {
        log.info("cacheName:{}, put key:{}", cacheName, key);
        //this.cache.put(key, value);
    }

}
