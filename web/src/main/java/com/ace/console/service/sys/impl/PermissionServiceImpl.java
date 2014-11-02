/**
 * Copyright (c) 2009-2018 https://github.com/denghp
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.ace.console.service.sys.impl;

import com.ace.console.bind.annotation.BaseComponent;
import com.ace.console.service.sys.PermissionService;
import com.ace.console.utils.Constants;
import com.ace.core.persistence.sys.entity.Permission;
import com.ace.core.persistence.sys.mapper.PermissionMapper;
import com.google.code.ssm.api.ParameterValueKeyProvider;
import com.google.code.ssm.api.ReadThroughSingleCache;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: denghp
 * @Date: 10/26/14 12:08 AM
 * @Description:
 */
@Service
public class PermissionServiceImpl extends GenericServiceImpl<Permission, Long> implements PermissionService {

    @BaseComponent
    @Resource
    private PermissionMapper permissionMapper;

    @Override
    @ReadThroughSingleCache(namespace = Constants.DEFAULT_PROJECT_NAME + ":permissions:selectById", expiration = 600)
    public Permission selectById(@ParameterValueKeyProvider Long aLong) {
        return permissionMapper.selectById(aLong);
    }
}
