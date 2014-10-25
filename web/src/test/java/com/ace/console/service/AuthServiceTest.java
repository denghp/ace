/**
 * Copyright (c) 2009-2018 https://github.com/denghp
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.ace.console.service;

import com.ace.commons.json.JsonUtils;
import com.ace.console.service.sys.AuthService;
import com.ace.core.persistence.sys.entity.Auth;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Set;

/**
 * @Author: denghp
 * @Date: 10/25/14 4:56 PM
 * @Description:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/*.xml", "classpath:*.xml"})
public class AuthServiceTest {
    private Logger logger = LoggerFactory.getLogger(AuthServiceTest.class);
    @Resource
    private AuthService authService;

    @Test
    public void getRoleIds() {
        Set<Long> roleIds = authService.getRoleIds(1l, null);
        for(Long roleId : roleIds) {
            logger.info("roleId : " + roleId);
        }
        Assert.assertNotNull(roleIds);
    }

    @Test
    public void getAuth() throws IOException {
        Auth auth = authService.selectOne(1l);
        logger.info(" auth : " + JsonUtils.getObjectMapper().writeValueAsString(auth));
        Assert.assertNotNull(auth);
    }

}
