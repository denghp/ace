/**
 * Copyright (c) 2009-2018 https://github.com/denghp
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.ace.core.persistence.sys.mapper.impl;

import com.ace.core.persistence.sys.entity.RoleResourcePermission;
import com.ace.core.persistence.sys.mapper.RoleResourcePermissionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: denghp
 * @Date: 11/26/14 9:42 PM
 * @Description:
 */
@Repository
public class RoleResourcePermissionMapperImpl extends GenericMapperImpl<RoleResourcePermission, Long> implements RoleResourcePermissionMapper {

    private Logger logger = LoggerFactory.getLogger(RoleResourcePermissionMapperImpl.class);

    @Override
    public int deleteRRPByRoleId(Long roleId) {
        if (roleId == null) {
            logger.warn("deleteRRPByRoleId failed, roleId is null.");
            return 0;
        }
        return getSqlSession().delete(getNamespace() + ".deleteRRPByRoleId", roleId);
    }

    @Override
    public RoleResourcePermission selectOne(Long roleId, Long resourceId) {
        if (roleId == null || resourceId == null) {
            logger.warn("params is invalid, roleId : {}, resourceId : {}", roleId, resourceId);
            return null;
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("roleId", roleId);
        params.put("resourceId", resourceId);
        return getSqlSession().selectOne(getNamespace() + ".selectOne", params);
    }

    @Override
    public List<RoleResourcePermission> getRRPListByRId(Long roleId) {
        if (roleId == null) {
            logger.warn("getRRPListByRId is invalid, roleId is null.");
            return null;
        }
        return getSqlSession().selectList(getNamespace() + ".getRRPListByRId", roleId);
    }
}
