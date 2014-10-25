package com.ace.core.persistence.sys.mapper;

import com.ace.core.persistence.sys.entity.User;

/**
 * @Project_Name: ace
 * @File: UserMapper
 * (C) Copyright ACE Corporation 2014 All Rights Reserved.
 * @Author: denghp
 * @Date: 10/18/14
 * @Time: 4:01 PM
 * @Description:
 */
public interface UserMapper extends GenericMapper<User, Long> {

    /**
     * 根据username查询USER
     * @param username
     * @return
     */
    public User getByUsername(String username);

    /**
     * 根据用户ID查询用户信息及相关组织机构信息
     * @param userId
     * @return
     */
    public User getUserDetails(Long userId);
}
