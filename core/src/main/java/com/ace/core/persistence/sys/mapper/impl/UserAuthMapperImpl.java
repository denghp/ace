/**
 * Copyright (c) 2009-2018 https://github.com/denghp
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.ace.core.persistence.sys.mapper.impl;

import com.ace.core.persistence.sys.entity.Auth;
import com.ace.core.persistence.sys.mapper.UserAuthMapper;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * @Author: denghp
 * @Date: 10/19/14 10:01 PM
 * @Description:
 */
@Repository
public class UserAuthMapperImpl extends GenericMapperImpl<Auth, Long> implements UserAuthMapper {

    @Override
    public Set<Long> findRoleIds(Long userId, Set<Long> groupIds, Set<Long> organizationIds, Set<Long> jobIds) {
        return null;
    }

    @Override
    public Auth findByUserId(Long userId) {
        return null;
    }

    @Override
    public Auth findByGroupId(Long groupId) {
        return null;
    }

    @Override
    public Auth findByOrganizationIdAndJobId(Long organizationId, Long jobId) {
        return null;
    }
}
