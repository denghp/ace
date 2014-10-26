package com.ace.console.shiro;

import com.ace.console.exception.AceException;
import com.ace.console.service.sys.UserService;
import com.ace.core.persistence.sys.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import javax.annotation.Resource;
import javax.security.auth.login.AccountNotFoundException;

/**
 * @Project_Name: ace
 * @File: ShiroTest
 * (C) Copyright ACE Corporation 2014 All Rights Reserved.
 * @Author: denghp
 * @Date: 10/18/14
 * @Time: 9:33 PM
 * @Description:
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/*.xml","classpath:*.xml"})
@TransactionConfiguration(defaultRollback = false)
public class ShiroTest {


    @Resource
    private UserService userService;

    @Test
    public void saveUser() throws AceException {
        User user = new User("admin","123456");
        userService.save(user);
    }

    @Test
    public void test() {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("admin1", "1123456");
        try {
            subject.login(token);
        } catch (LockedAccountException ex) {
            ex.printStackTrace();
        } catch (UnknownAccountException ex) {
            ex.printStackTrace();
        } catch (IncorrectCredentialsException ex) {
            ex.printStackTrace();
        }
        Assert.assertTrue(subject.isAuthenticated());


    }


}
