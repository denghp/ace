/**
 * Copyright (c) 2009-2018 https://github.com/denghp
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.ace.console.service.sys;

import com.ace.core.persistence.sys.entity.Role;
import com.ace.core.persistence.sys.entity.User;

import java.util.Set;

/**
 * @Author: denghp
 * @Date: 10/19/14 2:55 PM
 * @Description:
 */
public interface UserAuthService {

    /**
     * 查询用户相应的角色
     * @param user
     *       用户名
     * @return list<Role>
     *       返回角色列表
     */
    public Set<Role> findRoles(User user);

    /**
     *  查询角色
     * @param user
     *      用户名
     * @return
     *      角色列表
     */
    public Set<String> findStringRoles(User user);


    /**
     * 根据角色获取 权限字符串 如sys:admin
     *
     * @param user
     * @return
     */
    public Set<String> findPermissions(User user) ;

}
