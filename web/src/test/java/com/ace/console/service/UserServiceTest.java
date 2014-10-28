package com.ace.console.service;

import com.ace.commons.json.JsonUtils;
import com.ace.console.bind.annotation.BaseComponent;
import com.ace.console.exception.AceException;
import com.ace.console.service.sys.UserAuthService;
import com.ace.console.service.sys.UserService;
import com.ace.core.persistence.sys.entity.Role;
import com.ace.core.persistence.sys.entity.User;
import org.junit.Assert;
import org.junit.Before;
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
 * @Project_Name: ace-parent
 * @File: UserServiceTest
 * (C) Copyright ACE Corporation 2014 All Rights Reserved.
 * @Author: denghp
 * @Date: 10/16/14
 * @Time: 11:50 PM
 * @Description:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/*.xml","classpath:*.xml"})
public class UserServiceTest {
    private Logger logger = LoggerFactory.getLogger(UserServiceTest.class);
    @Resource
    private UserService userService;

    @Resource
    private UserAuthService userAuthService;

    protected String password = "123";
    protected User user;


    @Before
    public void init() {
        user = new User("demo_"+System.currentTimeMillis(), password);

    }

    @Test
    public void saveUser() throws AceException, IOException {
       User en = userService.save(user);
       logger.info("user : {}", JsonUtils.getObjectMapper().writeValueAsString(en));
       Assert.assertNotNull(user);
    }

    @Test
    public void selectUser() throws IOException {
        User user = userService.selectById(1l);
        logger.info("user : {}",JsonUtils.getObjectMapper().writeValueAsString(user));
        Assert.assertNotNull(user);
    }

    @Test
    public void selectDetails() throws IOException {
        User user = userService.getUserDetails(1l);
        logger.info("user : {}",JsonUtils.getObjectMapper().writeValueAsString(user));
        Assert.assertNotNull(user);
    }

    @Test
    public void findByUsername() throws IOException {
        User user = userService.getByUsername("admin");
        logger.info("user : {}",JsonUtils.getObjectMapper().writeValueAsString(user));
        Assert.assertNotNull(user);
    }

    @Test
    public void getRoleIds() {
        user = new User();
        user.setId(1l);
        Set<Role> roles = userAuthService.findRoles(user);
        logger.info("roles : {}", roles);
        roles = userAuthService.findRoles(user);
        logger.info("roles : {}", roles);
        Assert.assertNotNull(roles);
    }

    @Test
    public void findPermissions() {
        user = new User();
        user.setId(1l);
        Set<String> permissions = userAuthService.findPermissions(user);
        logger.info("permissions : {}", permissions);
        Assert.assertNotNull(permissions);
    }
}
