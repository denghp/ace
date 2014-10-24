package com.ace.core.persistence.sys.mapper.impl;

import com.ace.core.persistence.sys.entity.User;
import com.ace.core.persistence.sys.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

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
public class UserMapperImpl extends GenericeMapperImpl<User, Long> implements UserMapper {
    private Logger logger = LoggerFactory.getLogger(UserMapperImpl.class);
    public enum RdbOperation {

        GET_BY_USERNAME(".getByUsername"),
        GET_USER_DETAILS(".getUserDetails");

        private String value;

        private RdbOperation(String value) {
            this.value = value;
        }

        public String value() {
            return this.value;
        }
    }

    @Override
    public User getByUsername(String username) {
        return getSqlSession().selectOne(getNamespace() + RdbOperation.GET_BY_USERNAME.value(), username);
    }

    @Override
    public User getUserDetails(Long userId) {
        return getSqlSession().selectOne(getNamespace() + RdbOperation.GET_USER_DETAILS.value(), userId);
    }
}
