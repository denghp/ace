/**
 * Copyright (c) 2009-2018 https://github.com/denghp
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.ace.console.service.sys.impl;

import com.ace.console.bind.annotation.BaseComponent;
import com.ace.console.enums.Status;
import com.ace.console.exception.AceException;
import com.ace.console.service.sys.RoleService;
import com.ace.console.utils.Constants;
import com.ace.core.persistence.sys.entity.Role;
import com.ace.core.persistence.sys.entity.RoleResourcePermission;
import com.ace.core.persistence.sys.mapper.RoleMapper;
import com.ace.core.persistence.sys.mapper.RoleResourcePermissionMapper;
import com.google.code.ssm.api.*;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @Author: denghp
 * @Date: 10/25/14 8:32 PM
 * @Description:
 */
@Service
public class RoleServiceImpl extends GenericServiceImpl<Role, Long> implements RoleService {
    private Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);
    @Resource
    @BaseComponent
    private RoleMapper roleMapper;

    @Resource
    private RoleResourcePermissionMapper rrpMapper;

    @Override
    @ReadThroughSingleCache(namespace = Constants.DEFAULT_PROJECT_NAME + ":roles:getEnabledRoles", expiration = 600)
    public Set<Role> getEnabledRoles(@ParameterValueKeyProvider Set<Long> roleIds) {
        if (roleIds == null || roleIds.isEmpty()) {
            return null;
        }
        Set<Role> roleSet = Sets.newHashSet();
        //获取角色信息,过滤无效的role
        List<Role> roleList = roleMapper.getListRoleByIds(Lists.newArrayList(roleIds));
        for (Role role : roleList) {
            if (role.getEnabled() && roleIds.contains(role.getId())) {
                roleSet.add(role);
            }
        }
        return roleSet;
    }

    @ReadThroughSingleCache(namespace = Constants.DEFAULT_PROJECT_NAME + ":roles:getRoleResourceMaps", expiration = 600)
    public Map<Long, RoleResourcePermission> getRoleResourceMaps(@ParameterValueKeyProvider Long roleId) {
        List<Role> roles = roleMapper.getRoleResourcePermissions(roleId);
        Map<Long, RoleResourcePermission> rrpMaps = new HashMap<Long, RoleResourcePermission>();
        if (roles != null && roles.size() > 0) {
            List<RoleResourcePermission> rrpList = roles.get(0).getResourcePermissions();
            for (RoleResourcePermission rrp : rrpList) {
                rrpMaps.put(rrp.getResourceId(), rrp);
            }
        }
        return rrpMaps;
    }

    @Override
    @InvalidateSingleCache(namespace = Constants.DEFAULT_PROJECT_NAME + ":menus:getZTreeList")
    public void updateWithResourcePermission(Long[] resourceIds, @ParameterValueKeyProvider Role role)throws AceException {
        if (role == null || role.getId() == null || resourceIds == null
                && resourceIds.length <= 0) {
            logger.warn("updateWithResourcePermission failed, this params invalid");
            return;
        }

        try {
            //更新角色信息
            roleMapper.update(role);
            //删除当前role对应的所有资源信息
            rrpMapper.deleteRRPByRoleId(role.getId());
            for (Long resourceId : resourceIds) {
                if (resourceId != null) {
                    RoleResourcePermission rrp = new RoleResourcePermission(role.getId(), resourceId);
                    rrpMapper.insert(rrp);
                }
            }
        } catch (Exception ex) {
            logger.error("updateWithResourcePermission error , {}", ex);
            throw AceException.create(AceException.Code.SYSTEM_ERROR, "UpdateWithResourcePermission failed!");
        }
    }
}
