package com.ace.console.service.sys.impl;

import com.ace.console.bind.annotation.BaseComponent;
import com.ace.console.exception.AceException;
import com.ace.console.service.sys.PasswordService;
import com.ace.console.service.sys.UserService;
import com.ace.console.utils.Constants;
import com.ace.console.utils.PasswordHelper;
import com.ace.core.persistence.sys.entity.User;
import com.ace.core.persistence.sys.enums.UserStatus;
import com.ace.core.persistence.sys.mapper.UserMapper;
import com.google.code.ssm.api.ParameterValueKeyProvider;
import com.google.code.ssm.api.ReadThroughSingleCache;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

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
@Service
public class UserServiceImpl extends GenericServiceImpl<User, Long> implements UserService {

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Resource
    @BaseComponent
    private UserMapper userMapper;


    @Resource
    private PasswordService passwordService;

    /**
     * 创建用户
     * @param entity
     *              实体Bean
     * @return
     * @throws AceException
     */
    @Override
    public User save(User entity) throws AceException {
        //将用户的密码进行加密处理
        PasswordHelper.encryptPassword(entity);

        return super.save(entity);
    }

    /**
     *  修改密码
     * @param userId
     * @param newPassword
     * @return
     */
    @Override
    public boolean changePassword(Long userId, String newPassword) {
        //根据用户ID获取用户
        User user = userMapper.selectById(userId);
        user.setPassword(newPassword);
        //加密
        PasswordHelper.encryptPassword(user);

        userMapper.update(user);

        return true;
    }

    @Override
    public boolean correlationRoles(Long userId, Long... roleIds) {

        return false;
    }

    @Override
    public User getByUsername(String username) {
        if (StringUtils.isBlank(username)) {
            logger.warn("username is empty.");
            return null;
        }
        return userMapper.getByUsername(username);
    }

    @Override
    public User login(String username, String password) throws AceException {
        User user = getByUsername(username);

        if (user == null) {
            logger.warn("{} username not found.", username);
            throw new AceException.UserNotFoundException(username + " not found.");
        }

        if (user.getStatus() == UserStatus.blocked) {
            logger.warn("{} login failed, user is blocked!", username);
            throw new AceException.UserBlockedException();
        }
        //验证密码
        passwordService.validate(user, password);

        return user;
    }

    @Override
    public User getUserDetails(Long userId) {
        if (userId == null) {
            logger.warn("userId is null.");
            return null;
        }
        return userMapper.getUserDetails(userId);
    }

    @Override
    public User getByMobilePhone(String mobile) {
        return null;
    }

    @Override
    public User getByEmail(String email) {
        return null;
    }
}
