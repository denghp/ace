package com.ace.console.extra.session.impl;

import com.ace.console.cache.ShiroMemcachedManager;
import com.ace.console.extra.session.ShiroSessionRepository;
import com.ace.console.utils.Constants;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Collection;

/**
 * @Project_Name: ace
 * @File: SSMSessionRepository
 * (C) Copyright ACE Corporation 2013 All Rights Reserved.
 * @Author: denghp
 * @Date: 14-11-1
 * @Time: 下午1:49
 * @Description:
 */
public class SSMSessionRepository implements ShiroSessionRepository {

    private Logger logger = LoggerFactory.getLogger(SSMSessionRepository.class);

    private static final String SESSION_PREFIX = "session/";

    private final ShiroMemcachedManager shiroMemcachedManager;

    private Cache<Object, Object> cache;

    public SSMSessionRepository(final ShiroMemcachedManager shiroMemcachedManager) {
        this.shiroMemcachedManager = shiroMemcachedManager;
    }


    public Cache<Object, Object> getCache() {
        return shiroMemcachedManager.getCache(Constants.DEFAULT_MM_CACHE_NAME);
    }

    @Override
    public void saveSession(Session session) {
        cache.put(SESSION_PREFIX + session.getId(), session);
    }

    @Override
    public void deleteSession(Serializable sessionId) {
        cache.remove(SESSION_PREFIX + sessionId);
    }

    @Override
    public Session getSession(Serializable sessionId) {
        return (Session)cache.get(SESSION_PREFIX + sessionId);
    }

    //TODO
    @Override
    public Collection<Session> getAllSessions() {

        cache.keys();
        return null;
    }
}