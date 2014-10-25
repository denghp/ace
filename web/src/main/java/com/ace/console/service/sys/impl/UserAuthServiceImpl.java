/**
 * Copyright (c) 2009-2018 https://github.com/denghp
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.ace.console.service.sys.impl;

import com.ace.console.enums.Status;
import com.ace.console.service.sys.*;
import com.ace.core.persistence.sys.entity.Auth;
import com.ace.core.persistence.sys.entity.Role;
import com.ace.core.persistence.sys.entity.RoleResourcePermission;
import com.ace.core.persistence.sys.entity.User;
import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.google.common.collect.Sets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * @Author: denghp
 * @Date: 10/19/14 7:14 PM
 * @Description:
 */
@Service
public class UserAuthServiceImpl implements UserAuthService {

    private Logger logger = LoggerFactory.getLogger(UserAuthServiceImpl.class);

    @Resource
    private GroupService groupService;

    @Resource
    private GroupRelationService groupRelationService;

    @Resource
    private AuthService authService;

    @Resource
    private RoleService roleService;

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
        //TODO 根据用户所属的组获取相应的权限 default_group + userId 查找 group
        Set<Long> groupIds = Sets.newHashSet();
        //获取所有默认的组
        List<Long> defaultGroupIds = groupService.getDefaultGroupIds(Status.ENABLED.getIndex());
        groupIds.addAll(defaultGroupIds);
        groupIds.addAll(groupRelationService.getGroupIds(userId));
        logger.info("{} relation group ids : {}", user.getId(), groupIds);

        //获取所有的角色
        Set<Long> allRoles = authService.getRoleIds(userId,groupIds);
        logger.info("{} relation roleIds : {}", user.getId(), allRoles);

        return roleService.getEnabledRoles(allRoles);
    }

    @Override
    public Set<String> findStringRoles(User user) {

        Set<Role> roleSet = findRoles(user);

        return Sets.newHashSet(Collections2.transform(roleSet, new Function<Role, String>() {
            @Override
            public String apply(Role input) {
                return input.getRole();
            }
        }));
    }

    @Override
    public Set<String> findStringPermissions(User user) {
        Set<String> permissions = Sets.newHashSet();

        //获取所有角色
        Set<Role> roles = findRoles(user);
        //循环获取角色对应的资源及权限
        for (Role role : roles) {
            for (RoleResourcePermission roleResourcePermission : role.getResourcePermissions()) {

            }
        }
        return null;
    }
}
