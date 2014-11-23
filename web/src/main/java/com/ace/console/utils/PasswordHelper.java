package com.ace.console.utils;

import com.ace.console.utils.security.Md5Utils;

/**
 * @Author: denghp
 * @Date: 10/18/14
 * @Time: 8:09 PM
 * @Description:
 */
public class PasswordHelper {


    /**
     * 用户密码加密
     * @param username 用户名
     * @param password 密码
     * @param salt  密码种子
     */
    public static String encryptPassword(String username, String password, String salt) {

        return Md5Utils.hash(username + password + salt);

    }


}
