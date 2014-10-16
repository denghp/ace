package com.ace.console.service;

import com.ace.console.entity.UserEntity;
import com.ace.console.service.sys.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

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

    @Resource
    private UserService userService;

    @Test
    public void saveUser() {
        UserEntity userEntity = new UserEntity();
        userEntity.setAdmin(true);
        userEntity.setCount(10l);
        userEntity.setEmail("denghp@126.com");
        userEntity.setPassword("123");
        userService.save(userEntity);
    }
}
