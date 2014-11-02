package com.ace.console.cache;

import com.google.code.ssm.aop.support.PertinentNegativeNull;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.TimeoutException;

/**
 * @Project_Name: ace
 * @File: ShiroMemcachedCache
 * (C) Copyright ACE Corporation 2013 All Rights Reserved.
 * @Author: denghp
 * @Date: 14-11-2
 * @Time: 上午11:58
 * @Description:
 */
public class ShiroMemcachedCache<K,V> implements Cache<K,V> {

    private Logger LOGGER = LoggerFactory.getLogger(ShiroMemcachedCache.class);

    private final com.google.code.ssm.Cache cache;

    private final int expiration;

    private final boolean allowClear;

    public com.google.code.ssm.Cache getCache() {
        return cache;
    }

    public int getExpiration() {
        return expiration;
    }

    public boolean isAllowClear() {
        return allowClear;
    }

    public ShiroMemcachedCache(final com.google.code.ssm.Cache cache,final int expiration, final boolean allowClear) {
        this.cache = cache;
        this.expiration = expiration;
        this.allowClear = allowClear;
    }

    public ShiroMemcachedCache(final com.google.code.ssm.Cache cache,final int expiration) {
        this(cache, expiration, false);
    }

    public String getName() {
        return cache.getName();
    }

    @Override
    public V get(K key) throws CacheException {
        if (!cache.isEnabled()) {
            LOGGER.warn("Cache {} is disabled. Cannot get {} from cache", cache.getName(), key);
            return null;
        }

        Object value = getValue(key);
        if (value == null) {
            LOGGER.info("Cache miss. Get by key {} and from cache {}", new Object[] { key, cache.getName() });
            return null;
        }

        if (value instanceof PertinentNegativeNull) {
            return null;
        }

        LOGGER.info("Cache hit. Get by key {} from cache {} value '{}'", new Object[] { key, cache.getName(), value });
        return (V) value;
    }

    @Override
    public V put(K key, V value) throws CacheException {
        if (!cache.isEnabled()) {
            LOGGER.warn("Cache {} is disabled. Cannot put value under key {}", cache.getName(), key);
            return null;
        }

        if (key != null) {
            try {
                LOGGER.info("Put '{}' under key {} to cache {}", new Object[] { value, key, cache.getName() });

                Object store = value;
                if (value == null) {
                    store = PertinentNegativeNull.NULL;
                }

                cache.set(getKey(key), expiration, store, null);
            } catch (TimeoutException e) {
                LOGGER.warn("An error has ocurred for cache " + getName() + " and key " + getKey(key), e);
            } catch (com.google.code.ssm.providers.CacheException e) {
                LOGGER.warn("An error has ocurred for cache " + getName() + " and key " + getKey(key), e);
            } catch (RuntimeException e) {
                // do not propagate any exceptions
                LOGGER.warn("An error has ocurred for cache " + getName() + " and key " + getKey(key), e);
            }
        } else {
            LOGGER.info("Cannot put to cache {} because key is null", cache.getName());
        }
        return value;
    }

    /**
     * Removes the element which matches the key.
     *
     * <p>If no element matches, nothing is removed and no Exception is thrown.</p>
     *
     * @param key the key of the element to remove
     */
    @Override
    public V remove(K key) throws CacheException {
        if (!cache.isEnabled()) {
            LOGGER.warn("Cache {} is disabled. Cannot evict key {}", cache.getName(), key);
            return null;
        }
        Object value = null;
        if (key != null) {
            try {
                LOGGER.info("Evict {} from cache {}", key, cache.getName());
                value = getValue(getKey(key));
                cache.delete(getKey(key));
            } catch (TimeoutException e) {
                LOGGER.warn("An error has ocurred for cache " + cache.getName() + " and key " + getKey(key), e);
            } catch (com.google.code.ssm.providers.CacheException e) {
                LOGGER.warn("An error has ocurred for cache " + cache.getName() + " and key " + getKey(key), e);
            } catch (RuntimeException e) {
                // do not propagate any exceptions
                LOGGER.warn("An error has ocurred for cache " + cache.getName() + " and key " + getKey(key), e);
            }
        } else {
            LOGGER.info("Cannot evict from cache {} because key is null", cache.getName());
        }
        return (V)value;
    }

    /**
     * Removes all elements in the cache, but leaves the cache in a useable state.
     */
    @Override
    public void clear() throws CacheException {
        if (!cache.isEnabled()) {
            LOGGER.warn("Cache {} is disabled. Cannot clear cache.", cache.getName());
            return;
        }

        if (!allowClear) {
            LOGGER.error("Clearing cache '{}' is not allowed. To enable it set allowClear to true. "
                    + "Make sure that caches don't overlap (one memcached instance isn't used by more than one cache) "
                    + "otherwise clearing one cache will affect another.", getName());
            throw new IllegalStateException("Cannot clear cache " + getName());
        }
        try {
            LOGGER.info("Clear {}", cache.getName());
            cache.flush();
        } catch (TimeoutException e) {
            LOGGER.warn("An error has ocurred for cache " + getName(), e);
        } catch (com.google.code.ssm.providers.CacheException e) {
            LOGGER.warn("An error has ocurred for cache " + getName(), e);
        } catch (RuntimeException e) {
            // do not propagate any exceptions
            LOGGER.warn("An error has ocurred for cache " + getName(), e);
        }
    }

    @Override
    public int size() {
        try {
            //TODO: unsupported
            return 0;
        } catch (Throwable t) {
            throw new CacheException(t);
        }
    }

    @Override
    public Set<K> keys() {
        try {
            //TODO: unsupported
            return Collections.emptySet();
        } catch (Throwable t) {
            throw new CacheException(t);
        }
    }

    @Override
    public Collection<V> values() {
        try {
            //TODO: unsupported
            return Collections.emptyList();
        } catch (Throwable t) {
            throw new CacheException(t);
        }
    }


    private Object getValue(Object key) {
        Object value = null;
        try {
            value = cache.get(getKey(key), null);
        } catch (TimeoutException e) {
            LOGGER.warn("An error has ocurred for cache " + getName() + " and key " + getKey(key), e);
        } catch (com.google.code.ssm.providers.CacheException e) {
            LOGGER.warn("An error has ocurred for cache " + getName() + " and key " + getKey(key), e);
        } catch (RuntimeException e) {
            // do not propagate any exceptions
            LOGGER.warn("An error has ocurred for cache " + getName() + " and key " + getKey(key), e);
        }

        return value;
    }

    private String getKey(final Object key) {
        return key.toString();
    }
}
