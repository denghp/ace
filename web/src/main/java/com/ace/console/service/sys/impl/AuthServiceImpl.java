package com.ace.console.service.sys.impl;

import com.ace.console.bind.annotation.BaseComponent;
import com.ace.console.service.sys.AuthService;
import com.ace.core.persistence.sys.entity.Auth;
import com.ace.core.persistence.sys.mapper.AuthMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Set;

/**
 * @Project_Name: ace-web
 * @File: UserService
 * (C) Copyright ACE Corporation 2014 All Rights Reserved.
 * @Author: denghp
 * @Date: 10/15/14
 * @Time: 9:09 PM
 * @Description:
 */
@Service
public class AuthServiceImpl extends GenericServiceImpl<Auth, Long> implements AuthService {

    private Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

    @Resource
    @BaseComponent
    private AuthMapper authMapper;

    @Override
    public void addUserAuth(Long[] userIds, Auth auth) {

    }

    @Override
    public void addGroupAuth(Long[] groupIds, Auth auth) {

    }

    @CachePut(value = "Set<Long>", key = "#auth.getRoleIds")
    @Override
    public Set<Long> getRoleIds(Long userId, Set<Long> groupIds) {
        if (userId == null) {
            logger.error("userId is empty.");
            return null;
        }
        return authMapper.getRoleIds(userId, groupIds);
    }
}
