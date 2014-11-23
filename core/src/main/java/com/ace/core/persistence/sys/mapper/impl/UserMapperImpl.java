package com.ace.core.persistence.sys.mapper.impl;

import com.ace.core.persistence.sys.entity.User;
import com.ace.core.persistence.sys.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * @Project_Name: ace
 * @File: UserMapperImpl
 * (C) Copyright ACE Corporation 2014 All Rights Reserved.
 * @Author: denghp
 * @Date: 10/18/14
 * @Time: 3:56 PM
 * @Description:
 */
@Repository
public class UserMapperImpl extends GenericMapperImpl<User, Long> implements UserMapper {
    private Logger logger = LoggerFactory.getLogger(UserMapperImpl.class);
    public enum UserRdbOperation {

        GET_BY_USERNAME(".getByUsername"),
        GET_USER_DETAILS(".getUserDetails"),
        GET_BY_EMAIL(".getByEmail"),
        GET_BY_MOBILEPHONE(".getByMobilePhone"),
        CHANGE_PASSWORD(".changePassword");

        private String value;

        private UserRdbOperation(String value) {
            this.value = value;
        }

        public String value() {
            return this.value;
        }
    }


    @Override
    public User getByUsername(String username) {
        return getSqlSession().selectOne(getNamespace() + UserRdbOperation.GET_BY_USERNAME.value(), username);
    }

    @Override
    public User getUserDetails(Long userId) {
        return getSqlSession().selectOne(getNamespace() + UserRdbOperation.GET_USER_DETAILS.value(), userId);
    }

    @Override
    public User getByEmail(String email) {
        return getSqlSession().selectOne(getNamespace() + UserRdbOperation.GET_BY_EMAIL.value(), email);
    }

    @Override
    public boolean changePassword(Long userId, String newPassword) {
        if (userId == null) {
            logger.error("userId is null.");
            return false;
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("userId", userId);
        params.put("password", newPassword);
        try {
           int status = getSqlSession().update(getNamespace() + UserRdbOperation.CHANGE_PASSWORD.value(), params);
           if (status == 1) {
               return true;
           }
        } catch (Exception ex) {
            logger.error("changePassword error , userId : {}, newPassword : {}", userId, newPassword);
        }
        return false;
    }

    @Override
    public User getByMobilePhone(String mobile) {
        return getSqlSession().selectOne(getNamespace() + UserRdbOperation.GET_BY_MOBILEPHONE.value(), mobile);
    }
}
