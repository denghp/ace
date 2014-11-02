package com.ace.console.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @Project_Name: ace
 * @File: ShiroMemcachedManager
 * (C) Copyright ACE Corporation 2013 All Rights Reserved.
 * @Author: denghp
 * @Date: 14-11-2
 * @Time: 上午11:58
 * @Description:
 */
public class ShiroMemcachedManager implements CacheManager,InitializingBean {

    private final ConcurrentMap<String, ShiroMemcachedCache> cacheMap = new ConcurrentHashMap<String, ShiroMemcachedCache>();

    private final Set<String> cacheNames = new LinkedHashSet<String>();

    private Collection<ShiroMemcachedCache> caches;

    public Collection<ShiroMemcachedCache> getCaches() {
        return caches;
    }

    public void setCaches(Collection<ShiroMemcachedCache> caches) {
        this.caches = caches;
    }

    @Override
    public void afterPropertiesSet() {
        Assert.notEmpty(caches, "A collection of caches is required and cannot be empty");
        this.cacheMap.clear();

        // preserve the initial order of the cache names
        for (ShiroMemcachedCache cache : caches) {
            addCache(cache.getName(), cache);

        }
    }

    @Override
    public Cache getCache(final String name) {
        Cache cache = (Cache) this.cacheMap.get(name);
        if (cache == null) {
            return null;
        }

        return cache;
    }

    protected void addCache(final String name, final ShiroMemcachedCache cache) {
        this.cacheMap.put(name, cache);
        this.cacheNames.add(name);
    }

}
