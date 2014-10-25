/**
 * Copyright (c) 2009-2018 https://github.com/denghp
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.ace.core.persistence.sys.mapper;

import com.ace.core.persistence.sys.entity.Auth;

import java.util.Set;

/**
 * @Author: denghp
 * @Date: 10/19/14 6:47 PM
 * @Description:
 */
public interface UserAuthMapper extends GenericMapper<Auth, Long> {

    /**
     * 根据userId获取用户授权
     * @param userId
     * @return
     */
    public Auth findByUserId(Long userId);

    public Auth findByGroupId(Long groupId);

    public Auth findByOrganizationIdAndJobId(Long organizationId, Long jobId);

    /**
     * 根据用户ID查询所有的角色,根据groupIds,organizationIds,jobIds
     * @param userId
     * @param groupIds
     * @param organizationIds
     * @param jobIds
     * @return
     */
    public Set<Long> findRoleIds(Long userId, Set<Long> groupIds, Set<Long> organizationIds, Set<Long> jobIds);




}
