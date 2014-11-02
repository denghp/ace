package com.ace.console.service.sys.impl;

import com.ace.console.cache.ShiroMemcachedCache;
import com.ace.console.cache.ShiroMemcachedManager;
import com.ace.console.exception.AceException;
import com.ace.console.service.sys.PasswordService;
import com.ace.console.utils.Constants;
import com.ace.console.utils.security.Md5Utils;
import com.ace.core.persistence.sys.entity.User;
import net.sf.ehcache.Element;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @Project_Name: ace
 * @File: PasswordServiceImpl
 * (C) Copyright ACE Corporation 2013 All Rights Reserved.
 * @Author: denghp
 * @Date: 14-11-1
 * @Time: 下午10:17
 * @Description:
 */
@Service
public class PasswordServiceImpl implements PasswordService {
    private Logger logger = LoggerFactory.getLogger(PasswordServiceImpl.class);

    @Resource
    private ShiroMemcachedManager shiroMemcachedManager;

    private Cache<Object, Object> cache;

    private String KEY_PREFIX = "password/validate.";

    @Value(value = "${user.password.maxRetryCount}")
    private int maxRetryCount = 5;

    @PostConstruct
    public void init() {
        cache = shiroMemcachedManager.getCache(Constants.DEFAULT_MM_CACHE_NAME);
    }

    @Override
    public void validate(User user, String password) throws AceException{
        String username = user.getUsername();

        int retryCount = 0;
        Element cacheElement = (Element)cache.get(KEY_PREFIX + username);
        if (cacheElement != null) {
            retryCount = (Integer) cacheElement.getObjectValue();
            if (retryCount >= maxRetryCount) {
                logger.info(
                        username,
                        "passwordError",
                        "password error, retry limit exceed! password: {},max retry count {}",
                        password, maxRetryCount);
                //throw new UserPasswordRetryLimitExceedException(maxRetryCount);
                throw AceException.create(AceException.Code.USER_PASSWORD_RETRY_COUNT,"password error, retry limit exceed! password: {},max retry count "+retryCount);
            }
        }

        if (!matches(user, password)) {
            cache.put(KEY_PREFIX+username,new Element(username, ++retryCount));
            logger.info(
                    username,
                    "passwordError",
                    "password error! password: {} retry count: {}",
                    password, retryCount);
            throw AceException.create(AceException.Code.USER_PASSWORD_NOT_MATCH,"password error! password: "+password+" retry count: "+retryCount);
        } else {
            clearLoginRecordCache(username);
        }
    }

    @Override
    public boolean matches(User user, String newPassword) {
        return user.getPassword().equals(encryptPassword(user.getUsername(), newPassword, user.getSalt()));
    }

    @Override
    public void clearLoginRecordCache(String username) {
        if (StringUtils.isNotBlank(username)) {
            cache.remove(username);
            logger.info("Cache evict {}", username);
        }
    }

    @Override
    public String encryptPassword(String username, String password, String salt) {
        return Md5Utils.hash(username + password + salt);
    }
}
