package com.ace.core.persistence.sys.mapper;

import com.ace.core.persistence.sys.entity.Auth;
import com.ace.core.persistence.sys.entity.Group;

import java.util.List;
import java.util.Set;

/**
 * @Project_Name: ace
 * @File: GroupMapper
 * (C) Copyright ACE Corporation 2014 All Rights Reserved.
 * @Author: denghp
 * @Date: 10/18/14
 * @Time: 4:01 PM
 * @Description:
 */
public interface AuthMapper extends GenericMapper<Auth, Long> {

    /**
     * 根据用户ID,所属组IDS查询相关的角色
     * @param userId
     *        用户ID
     * @param groupIds
     *        用户所属组的id集合
     * @return
     */
    public Set<Long> getRoleIds(Long userId, Set<Long> groupIds);

}
