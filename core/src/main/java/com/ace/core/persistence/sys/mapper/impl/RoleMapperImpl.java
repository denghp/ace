/**
 * Copyright (c) 2009-2018 https://github.com/denghp
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.ace.core.persistence.sys.mapper.impl;

import com.ace.core.persistence.sys.entity.Role;
import com.ace.core.persistence.sys.mapper.RoleMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: denghp
 * @Date: 10/25/14 10:09 PM
 * @Description:
 */
@Repository
public class RoleMapperImpl extends GenericMapperImpl<Role, Long> implements RoleMapper {

    private Logger logger = LoggerFactory.getLogger(RoleMapperImpl.class);
    public enum RoleRdbOperation {

        GET_LIST_ROLE_BY_IDS(".getListRoleByIds"),
        GET_ROLE_RESOURCE_PERMISSIONS(".getRoleResourcePermissions");

        private String value;

        private RoleRdbOperation(String value) {
            this.value = value;
        }

        public String value() {
            return this.value;
        }
    }
    @Override
    public List<Role> getListRoleByIds(List<Long> roleIds) {
        if (roleIds == null) {
            logger.warn("roleIds is null.");
            return null;
        }
        return getSqlSession().selectList(getNamespace() + RoleRdbOperation.GET_LIST_ROLE_BY_IDS.value(), roleIds);
    }

    @Override
    public List<Role> getRoleResourcePermissions(Long roleId) {
        return getSqlSession().selectList(getNamespace() + RoleRdbOperation.GET_ROLE_RESOURCE_PERMISSIONS.value(), roleId);
    }
}
