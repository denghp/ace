/**
 * Copyright (c) 2009-2018 https://github.com/denghp
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.ace.core.persistence.sys.mapper;

import com.ace.core.persistence.sys.entity.Role;

import java.util.List;

/**
 * @Author: denghp
 * @Date: 10/25/14 10:09 PM
 * @Description:
 */
public interface RoleMapper extends GenericMapper<Role, Long> {

    /**
     * 根据角色ID获取角色信息
     * @param roleIds
     * @return
     */
    public List<Role> getListRoleByIds(List<Long> roleIds);


    /**
     * 根据 roleId获取相关的ResourcePermission
     * @param roleId
     * @return List<Role>
     */
    public List<Role> getRoleResourcePermissions(Long roleId);

}
