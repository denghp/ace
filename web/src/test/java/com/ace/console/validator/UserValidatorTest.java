/**
 * Copyright (c) 2009-2018 https://github.com/denghp
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.ace.console.validator;

import com.ace.console.exception.AceException;
import com.ace.core.persistence.sys.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Author: denghp
 * @Date: 10/19/14 9:23 PM
 * @Description:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/*.xml","classpath:*.xml"})
public class UserValidatorTest {
    private Logger logger = LoggerFactory.getLogger(UserValidatorTest.class);
    private User user;

    @Test
    public void validatorUsername(){
        user = new User("","adm");
        try {
            ValidatorUtils.validate(user);
        } catch (AceException ex) {
            ex.printStackTrace();
        }
        //logger.info("username.validator : {}", message);
    }

}
