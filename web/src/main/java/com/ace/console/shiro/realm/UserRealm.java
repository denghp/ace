package com.ace.console.shiro.realm;

import com.ace.console.service.sys.UserService;
import com.ace.core.persistence.entity.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.Set;

/**
 * @Project_Name: ace-web
 * @File: UserRealm
 * (C) Copyright ACE Corporation 2014 All Rights Reserved.
 * @Author: denghp
 * @Date: 10/15/14
 * @Time: 9:06 PM
 * @Description:
 */
public class UserRealm extends AuthorizingRealm {

    private Logger logger = LoggerFactory.getLogger(UserRealm.class);

    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * 给登录用户授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //根据授权信息获取用户名
        String username = (String)principalCollection.getPrimaryPrincipal();

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //根据用户获取相应角色并授权
        authorizationInfo.setRoles(userService.findRoles(username));
        Set<String> permissions = userService.findPermissions(username);
        //获取用户相应的权限
        authorizationInfo.setStringPermissions(permissions);
        if (logger.isDebugEnabled()) {
            for (String permission : permissions) {
                logger.debug("User : {} , permission : {} ", username, permission);
            }
        }
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获取用户名
        String username = (String)authenticationToken.getPrincipal();

        //根据用户名获取相应信息
        User user = userService.findByUsername(username);

        //用户不存在
        if (user == null) {
            logger.warn("user.not.exists, username : {}", username);
            throw new UnknownAccountException("user.not.exists");
        }

        //当前用户已经被锁定
        if(Boolean.TRUE.equals(user.getLocked())) {
            logger.warn("user.blocked, username : {}", username);
            throw new LockedAccountException("user.blocked"); //帐号锁定
        }

        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user.getUsername(),
                user.getPassword(),
                ByteSource.Util.bytes(user.getSalt()),//salt=username+salt,
                getName());
        return info;
    }
}
