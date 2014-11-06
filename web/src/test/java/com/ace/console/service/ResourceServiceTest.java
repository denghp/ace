/**
 * Copyright (c) 2009-2018 https://github.com/denghp
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.ace.console.service;

import com.ace.console.service.sys.ResourceService;
import com.ace.core.persistence.sys.entity.Menu;
import com.ace.core.persistence.sys.entity.Resources;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: denghp
 * @Date: 10/26/14 10:31 AM
 * @Description:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/*.xml","classpath:*.xml"})
public class ResourceServiceTest {
    private Logger logger = LoggerFactory.getLogger(ResourceServiceTest.class);

    @Resource
    private ResourceService resourceService;


    @Test
    public void findMenusTest() {
        List<Menu> menuList = resourceService.findMenus();

        for (Menu menu : menuList) {
            logger.info("menu : {}", menu.toString());
        }
        Assert.assertNotNull(menuList);
    }

    @Test
    public void selectById() {
        List<Resources> resourceList = resourceService.selectAll();
        for (Resources resources : resourceList) {
            resourceService.selectById(resources.getId());
        }
    }


}
