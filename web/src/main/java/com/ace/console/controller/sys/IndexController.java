/**
 * Copyright (c) 2009-2018 https://github.com/denghp
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.ace.console.controller.sys;

import com.ace.console.bind.annotation.CurrentUser;
import com.ace.console.service.sys.ResourceService;
import com.ace.core.persistence.sys.entity.Menu;
import com.ace.core.persistence.sys.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created with ace-erp.
 * User: denghp
 * Date: 10/18/13
 * Time: 9:47 PM
 */
@Controller
public class IndexController {
    private Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private ResourceService resourceService;

    @RequestMapping(value = {"/{index:index;?.*}"})
    public String index(@CurrentUser User user,Model model) {
        List<Menu> menuList = resourceService.findMenus(user);
        model.addAttribute("menuList", menuList);

        return "/index";
    }

    @RequestMapping("/{main:main;?.*}")
    public String main(@CurrentUser User user,Model model) {


        return "/main";
    }



}
