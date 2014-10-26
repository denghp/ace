/**
 * Copyright (c) 2009-2018 https://github.com/denghp
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.ace.console.controller.sys;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: denghp
 * @Date: 10/26/14 9:08 PM
 * @Description:
 */
@Controller
public class LogoutController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogoutController.class);

    /**
     * 用户退出。
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public String logout() {
        try {
            SecurityUtils.getSubject().logout();
            return "OK";
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return "用户退出失败。";
        }
    }

}
