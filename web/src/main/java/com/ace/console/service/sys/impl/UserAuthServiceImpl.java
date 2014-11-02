/**
 * Copyright (c) 2009-2018 https://github.com/denghp
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.ace.console.service.sys.impl;

import com.ace.console.enums.Status;
import com.ace.console.exception.AceException;
import com.ace.console.service.sys.*;
import com.ace.console.utils.Constants;
import com.ace.core.persistence.sys.entity.*;
import com.google.code.ssm.api.ParameterValueKeyProvider;
import com.google.code.ssm.api.ReadThroughSingleCache;
import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.google.common.collect.Sets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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

    @Resource
    private ResourceService resourceService;

    @Resource
    private PermissionService permissionService;


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
        List<Long> validGroupIds = groupRelationService.getGroupIds(userId);
        groupIds.addAll(defaultGroupIds);
        if (validGroupIds != null) {
            groupIds.addAll(validGroupIds);
        }
        logger.info("{} relation group ids : {}", user.getId(), groupIds);

        //获取所有的角色
        Set<Long> allRoles = authService.getRoleIds(userId,groupIds);
        logger.info("{} relation roleIds : {}", user.getId(), allRoles);

        return roleService.getEnabledRoles(allRoles);
    }

    @Override
    @ReadThroughSingleCache(namespace = Constants.DEFAULT_PROJECT_NAME + ":auth:findStringRoles", expiration = 600)
    public Set<String> findStringRoles(@ParameterValueKeyProvider User user){

        Set<Role> roleSet = findRoles(user);

        return Sets.newHashSet(Collections2.transform(roleSet, new Function<Role, String>() {
            @Override
            public String apply(Role input) {
                return input.getRole();
            }
        }));
    }

    @Override
    @ReadThroughSingleCache(namespace = Constants.DEFAULT_PROJECT_NAME + ":auth:findPermissions", expiration = 600)
    public Set<String> findPermissions(@ParameterValueKeyProvider User user) {

        Set<String> permissions = Sets.newHashSet();

        //获取所有角色
        Set<Role> roles = findRoles(user);
        //循环获取角色对应的资源及权限
        for (Role role : roles) {
            for (RoleResourcePermission rrp : role.getResourcePermissions()) {
                com.ace.core.persistence.sys.entity.Resource resource = resourceService.selectById(rrp.getResourceId());

                String actualResourceIdentity = resourceService.findActualResourceIdentity(resource);

                //不可用 即没查到 或者标识字符串不存在
                if (resource == null || StringUtils.isEmpty(actualResourceIdentity) || Status.DISABLE.getIndex() == resource.getEnabled()) {
                    continue;
                }

                for (Long permissionId : rrp.getPermissionIds()) {
                    Permission permission = permissionService.selectById(permissionId);

                    //不可用
                    if (permission == null || Status.DISABLE.getIndex() == permission.getEnabled()) {
                        continue;
                    }
                    permissions.add(actualResourceIdentity + ":" + permission.getPermission());

                }
            }
        }
        return permissions;
    }
}
