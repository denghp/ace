package com.ace.core.persistence.mapper.impl;

import com.ace.core.persistence.entity.User;
import com.ace.core.persistence.mapper.UserMapper;

/**
 * @Project_Name: ace
 * @File: UserMapperImpl
 * (C) Copyright ACE Corporation 2014 All Rights Reserved.
 * @Author: denghp
 * @Date: 10/18/14
 * @Time: 3:56 PM
 * @Description:
 */
public class UserMapperImpl extends GenericeMapperImpl<User, Long> implements UserMapper {

    public enum RdbOperation {

        FIND_BY_USERNAME(".Mapper.findByUsername");

        private String value;

        private RdbOperation(String value) {
            this.value = value;
        }

        public String value() {
            return this.value;
        }
    }

    @Override
    public User findByUsername(String username) {
        return getSqlSession().selectOne(getNamespace() + RdbOperation.FIND_BY_USERNAME, username);
    }

}
