package com.ace.console.shiro.realm;

import com.ace.console.exception.AceException;
import com.ace.console.service.sys.UserAuthService;
import com.ace.console.service.sys.UserService;
import com.ace.core.persistence.sys.entity.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
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

    @Resource
    private UserService userService;

    @Resource
    private UserAuthService userAuthService;

    /**
     * 给登录用户授权
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //根据授权信息获取用户名
        String username = (String) principalCollection.getPrimaryPrincipal();

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //获取用户信息
        User user = userService.getByUsername(username);
        //根据用户获取相应角色并授权
        //TODO: 获取用户角色和权限
        authorizationInfo.setRoles(userAuthService.findStringRoles(user));
        Set<String> permissions = userAuthService.findStringPermissions(user);
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
        UsernamePasswordToken upToken = (UsernamePasswordToken) authenticationToken;
        String username = upToken.getUsername().trim();
        String password = "";
        if (upToken.getPassword() != null) {
            password = new String(upToken.getPassword());
        }

        User user = null;

        try {
            user = userService.login(username, password);
        } catch (AceException.UserNotFoundException e) {
            throw new UnknownAccountException("user.not.exists", e);
        } catch (AceException.UserPasswordNotMatchException e) {
            throw new AuthenticationException("user.password.not.match", e);
        } catch (AceException.UserPasswordRetryCount e) {
            throw new ExcessiveAttemptsException("user.password.retry.limit.exceed", e);
        } catch (AceException.UserBlockedException e) {
            throw new LockedAccountException("user.blocked", e);
        } catch (Exception e) {
            logger.error("login error", e);
            throw new AuthenticationException("user.unknown.error", e);
        }


//        String username = (String)authenticationToken.getPrincipal();
//
//        User user = userService.getByUsername(username);
//
//        if (user == null) {
//            throw new UnknownAccountException();
//        }
//
//        if (user.getStatus().equals(UserStatus.blocked)) {
//            throw new LockedAccountException();
//        }

        //SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user.getUsername(), password.toCharArray(), getName());
        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
//        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(
//                user.getUsername(), //用户名
//                user.getPassword(), //密码
//                ByteSource.Util.bytes(user.getCredentialsSalt()),//salt=username+salt
//                getName()  //realm name
//        );

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user.getUsername(), password.toCharArray(), getName());
        return info;
    }
}
