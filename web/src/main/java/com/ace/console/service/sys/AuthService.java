package com.ace.console.service.sys;

import com.ace.console.service.GenericService;
import com.ace.core.persistence.sys.entity.Auth;

import java.io.IOException;
import java.util.Set;

/**
 * @Project_Name: ace-web
 * @File: UserService
 * (C) Copyright ACE Corporation 2014 All Rights Reserved.
 * @Author: denghp
 * @Date: 10/15/14
 * @Time: 9:09 PM
 * @Description:
 */
public interface AuthService extends GenericService<Auth, Long> {

    /**
     *  添加用户角色权限
     * @param userIds
     * @param auth
     */
    public void  addUserAuth(Long[] userIds, Auth auth);

    /**
     * 给group添加角色权限
     * @param groupIds
     * @param auth
     */
    public void addGroupAuth(Long[] groupIds, Auth auth);

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
