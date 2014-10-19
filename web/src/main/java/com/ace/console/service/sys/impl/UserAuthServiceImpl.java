/**
 * Copyright (c) 2009-2018 https://github.com/denghp
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.ace.console.service.sys.impl;

import com.ace.console.service.sys.UserAuthService;
import com.ace.core.persistence.sys.entity.Role;
import com.ace.core.persistence.sys.entity.User;
import com.ace.core.persistence.sys.entity.UserAuth;
import com.google.common.collect.Sets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @Author: denghp
 * @Date: 10/19/14 7:14 PM
 * @Description:
 */
@Service
public class UserAuthServiceImpl extends AbstractService<UserAuth, Long> implements UserAuthService {

    private Logger logger = LoggerFactory.getLogger(UserAuthServiceImpl.class);

    @Override
    public Set<Role> findRoles(User user) {
        if (user == null) {
            logger.warn("findRoles then user is null.");
            return Sets.newHashSet();
        }


        return null;
    }

    @Override
    public Set<String> findStringRoles(User user) {
        return null;
    }

    @Override
    public Set<String> findStringPermissions(User user) {
        return null;
    }
}
