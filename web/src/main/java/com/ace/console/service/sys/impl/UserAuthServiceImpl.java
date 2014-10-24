/**
 * Copyright (c) 2009-2018 https://github.com/denghp
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.ace.console.service.sys.impl;

import com.ace.console.service.sys.GroupService;
import com.ace.console.service.sys.UserAuthService;
import com.ace.core.persistence.sys.entity.Auth;
import com.ace.core.persistence.sys.entity.Role;
import com.ace.core.persistence.sys.entity.User;
import com.ace.core.persistence.sys.entity.UserOrganizationJob;
import com.google.common.collect.Sets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Set;

/**
 * @Author: denghp
 * @Date: 10/19/14 7:14 PM
 * @Description:
 */
@Service
public class UserAuthServiceImpl extends GenericeServiceImpl<Auth, Long> implements UserAuthService {

    private Logger logger = LoggerFactory.getLogger(UserAuthServiceImpl.class);

    @Resource
    private GroupService groupService;

    @Override
    public Set<Role> findRoles(User user) {
        if (user == null) {
            logger.warn("findRoles then user is null.");
            return Sets.newHashSet();
        }

        Long userId = user.getId();
        //TODO: 目前不考虑用户继承job,organization的角色
//        Set<Long[]> organizationJobIds = Sets.newHashSet();
//        Set<Long> organizationIds = Sets.newHashSet();
//        Set<Long> jobIds = Sets.newHashSet();
//
//        for (UserOrganizationJob o : user.getUserOrganizationJobs()) {
//            Long organizationId = o.getOrganizationId();
//            Long jobId = o.getJobId();
//
//            if (organizationId != null && jobId != null && organizationId != 0L && jobId != 0L) {
//                organizationJobIds.add(new Long[]{organizationId, jobId});
//            }
//            organizationIds.add(organizationId);
//            jobIds.add(jobId);
//        }

        //TODO 目前默认子会继承父 后续实现添加flag控制是否继承
        //default_group + userId 查找 group

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
