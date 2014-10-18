package com.ace.console.service.sys.impl;

import com.ace.console.annotation.BaseComponent;
import com.ace.console.service.sys.UserService;
import com.ace.core.persistence.entity.User;
import com.ace.core.persistence.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;


/**
 * @Project_Name: ace-parent
 * @File: UserService
 * (C) Copyright ACE Corporation 2014 All Rights Reserved.
 * @Author: denghp
 * @Date: 10/16/14
 * @Time: 11:46 PM
 * @Description:
 */
public class UserServiceImpl extends AbstractService<User, Long> implements UserService {

    @Autowired
    @BaseComponent
    private UserMapper userMapper;
}
