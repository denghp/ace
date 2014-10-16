package com.ace.console.service.sys.impl;

import com.ace.console.common.mybatis.BaseMapper;
import com.ace.console.dao.IGenericeDao;
import com.ace.console.entity.UserEntity;
import com.ace.console.exception.AceException;
import com.ace.console.service.sys.UserService;
import com.ace.core.persistence.entity.User;

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
public class UserServiceImpl implements UserService {

    @Resource
    private IGenericeDao<UserEntity> genericeDao;



    @Override
    public Long save(UserEntity entity)  {

        return genericeDao.save("user",entity);
    }
}
