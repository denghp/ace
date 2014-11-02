package com.ace.console.service.sys;

import com.ace.console.exception.AceException;
import com.ace.console.service.GenericService;
import com.ace.core.persistence.sys.entity.User;

/**
 * @Project_Name: ace-web
 * @File: UserService
 * (C) Copyright ACE Corporation 2014 All Rights Reserved.
 * @Author: denghp
 * @Date: 10/15/14
 * @Time: 9:09 PM
 * @Description:
 */
public interface UserService extends GenericService<User, Long> {

    public User login (String username, String password) throws AceException;

    /**
     * 修改密码
     * @param userId
     * @param newPassword
     * @return
     */
    public boolean changePassword(Long userId, String newPassword);


    /**
     * 添加用户-角色关系
     * @param userId
     * @param roleIds
     */
    public boolean correlationRoles(Long userId, Long... roleIds);


    /**
     * 根据用户名查询用户信息
     * @param username
     *      用户名
     * @return
     *      返回用户基本信息
     */
    public User getByUsername(String username);

    /**
     * 根据用户ID查询用户详细信息
     * @param userId
     * @return
     */
    public User getUserDetails(Long userId);


}
