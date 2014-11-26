/**
 * Copyright (c) 2009-2018 https://github.com/denghp
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.ace.console.service.sys;

import com.ace.console.exception.AceException;
import com.ace.console.service.GenericService;
import com.ace.core.persistence.sys.entity.Role;
import com.ace.core.persistence.sys.entity.RoleResourcePermission;

import java.util.Map;
import java.util.Set;

/**
 * @Author: denghp
 * @Date: 10/25/14 8:29 PM
 * @Description:
 */
public interface RoleService extends GenericService<Role, Long> {

    /**
     * 获取有效的角色集合
     * @param roleIds
     * @return
     */
    public Set<Role> getEnabledRoles(Set<Long> roleIds);

    /**
     * 根据角色Id获取权限
     * @param roleId
     * @return
     */
    public Map<Long, RoleResourcePermission> getRoleResourceMaps(Long roleId);

    /**
     * 更新角色与资源的关系
     * @param resourceIds
     * @param role
     */
    public void updateWithResourcePermission(Long[] resourceIds, Role role) throws AceException;

}
