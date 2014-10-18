package com.ace.console.service.sys;

import com.ace.console.service.GenericService;
import com.ace.core.persistence.entity.User;
import org.springframework.stereotype.Service;

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
@Service
public interface UserService extends GenericService<User, Long> {

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
     * 根据用户名查找其角色
     * @param username
     * @return
     */
    public Set<String> findRoles(String username);

    /**
     * 根据用户名查找其权限
     * @param username
     * @return
     */
    public Set<String> findPermissions(String username);

    public User findByUsername(String username);
}
