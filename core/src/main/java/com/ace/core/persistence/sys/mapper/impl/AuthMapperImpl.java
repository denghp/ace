/**
 * Copyright (c) 2009-2018 https://github.com/denghp
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.ace.core.persistence.sys.mapper.impl;

import com.ace.core.persistence.sys.entity.Auth;
import com.ace.core.persistence.sys.mapper.AuthMapper;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * @Author: denghp
 * @Date: 10/25/14 4:52 PM
 * @Description:
 */
@Repository
public class AuthMapperImpl extends GenericMapperImpl<Auth, Long> implements AuthMapper {


    @Override
    public Set<Long> getRoleIds(Long userId, Set<Long> groupIds) {
        return null;
    }
}
