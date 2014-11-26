/**
 * Copyright (c) 2009-2018 https://github.com/denghp
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.ace.core.persistence.sys.mapper;

import com.ace.core.persistence.sys.entity.RoleResourcePermission;

import java.util.List;
import java.util.Map;

/**
 * @Author: denghp
 * @Date: 11/26/14 9:38 PM
 * @Description:
 */
public interface RoleResourcePermissionMapper extends GenericMapper<RoleResourcePermission, Long> {

    /**
     *  根据角色ID AND 资源ID查询角色资源关系对象
     * @param roleId  角色ID
     * @param resourceId 资源ID
     * @return
     */
    public RoleResourcePermission selectOne(Long roleId, Long resourceId);

    /**
     * 根据角色ID获取相应的角色资源权限集合
     * @param roleId 角色ID
     * @return
     */
    public List<RoleResourcePermission> getRRPListByRId(Long roleId);

    /**
     * 根据角色ID删除相应的角色资源权限关系
     * @param roleId
     * @return
     */
    public int deleteRRPByRoleId(Long roleId);

}
