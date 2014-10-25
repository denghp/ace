/**
 * Copyright (c) 2009-2018 https://github.com/denghp
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.ace.core.persistence.sys.mapper.impl;

import com.ace.core.persistence.sys.entity.Auth;
import com.ace.core.persistence.sys.enums.RdbParams;
import com.ace.core.persistence.sys.mapper.AuthMapper;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Author: denghp
 * @Date: 10/25/14 4:52 PM
 * @Description:
 */
@Repository
public class AuthMapperImpl extends GenericMapperImpl<Auth, Long> implements AuthMapper {
    private Logger logger = LoggerFactory.getLogger(AuthMapperImpl.class);

    public enum AuthOperation {

        GET_ROLE_IDS(".getRoleIds");

        private String value;

        private AuthOperation(String value) {
            this.value = value;
        }

        public String value() {
            return this.value;
        }
    }


    /**
     * 根据用户ID,所属组ID查询相关的角色
     * @param userId
     *        用户ID
     * @param groupIds
     *        用户所属组的id集合
     * @return
     */
    @Override
    public Set<Long> getRoleIds(Long userId, Set<Long> groupIds) {

        if (userId == null) {
            logger.warn("userId is null.");
            return null;
        }
        Map<String, Object> params = Maps.newHashMap();
        params.put(RdbParams.USER_ID.value(), userId);
        if (groupIds != null) {
            params.put(RdbParams.GROUP_IDS.value(), groupIds);
        }
        List<Auth> authList = getSqlSession().selectList(getNamespace() + AuthOperation.GET_ROLE_IDS.value(), params);
        Set<Long> roles = Sets.newHashSet();
        for (Auth auth : authList) {
            roles.addAll(auth.getRoleIds());
        }
        return roles;
    }
}
