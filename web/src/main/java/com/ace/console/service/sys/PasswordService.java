package com.ace.console.service.sys;

import com.ace.console.exception.AceException;
import com.ace.core.persistence.sys.entity.User;

/**
 * @Project_Name: ace
 * @File: PasswordService
 * (C) Copyright ACE Corporation 2013 All Rights Reserved.
 * @Author: denghp
 * @Date: 14-11-1
 * @Time: 下午10:05
 * @Description:
 */
public interface PasswordService {

    /**
     * 验证用户名密码
     * @param user
     * @param password
     */
    public void validate(User user, String password) throws AceException;

    /**
     * 验证用户名密码是否正确
     * @param user
     * @param newPassword
     * @return
     */
    public boolean matches(User user, String newPassword);

    /**
     * 清空缓存中的用户
     * @param username
     */
    public void clearLoginRecordCache(String username);

}
