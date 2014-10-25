/**
 * Copyright (c) 2009-2018 https://github.com/denghp
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.ace.console.service.sys.impl;

import com.ace.console.annotation.BaseComponent;
import com.ace.console.enums.Status;
import com.ace.console.service.sys.RoleService;
import com.ace.core.persistence.sys.entity.Role;
import com.ace.core.persistence.sys.entity.RoleResourcePermission;
import com.ace.core.persistence.sys.mapper.RoleMapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
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

    @Resource
    @BaseComponent
    private RoleMapper roleMapper;

    @Override
    public Set<Role> getEnabledRoles(Set<Long> roleIds) {
        Set<Role> roleSet = Sets.newHashSet();
        //获取角色信息,过滤无效的role
        List<Role> roleList = roleMapper.getListRoleByIds(Lists.newArrayList(roleIds));
        for (Role role : roleList) {
            if (Status.ENABLED.getIndex() == role.getEnabled() && roleIds.contains(role.getId())) {
                roleSet.add(role);
            }
        }
        return roleSet;
    }

    public Map<Long, RoleResourcePermission> getRoleResourceMaps(Long roleId) {
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
}
