package com.ace.console.service.sys.impl;

import com.ace.commons.json.JsonUtils;
import com.ace.console.annotation.BaseComponent;
import com.ace.console.service.GenericService;
import com.ace.console.service.sys.AuthService;
import com.ace.core.persistence.sys.entity.Auth;
import com.ace.core.persistence.sys.mapper.AuthMapper;
import org.codehaus.jackson.JsonGenerationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
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

    @Override
    public Set<Long> getRoleIds(Long userId, Set<Long> groupIds) {
        Auth auth = authMapper.selectOne(1l);

        return auth.getRoleIds();
    }
}
