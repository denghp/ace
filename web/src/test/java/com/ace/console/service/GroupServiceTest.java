package com.ace.console.service;

import com.ace.commons.json.JsonUtils;
import com.ace.console.exception.AceException;
import com.ace.console.service.sys.GroupRelationService;
import com.ace.console.service.sys.GroupService;
import com.ace.core.persistence.sys.entity.Group;
import com.ace.core.persistence.sys.enums.GroupType;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @Project_Name: ace-parent
 * @File: UserServiceTest
 * (C) Copyright ACE Corporation 2014 All Rights Reserved.
 * @Author: denghp
 * @Date: 10/16/14
 * @Time: 11:50 PM
 * @Description:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/*.xml","classpath:*.xml"})
public class GroupServiceTest {
    private Logger logger = LoggerFactory.getLogger(GroupServiceTest.class);

    @Resource
    private GroupService groupService;

    @Resource
    private GroupRelationService groupRelationService;

    Group group;

    @Before
    public void init() throws AceException {
        group = new Group();
        group.setName("运维组");
        group.setType(GroupType.user);
        group.setCreateTime(new DateTime());
    }

    @Test
    public void getGroupTest() throws AceException,IOException {
        groupService.save(group);
        group = groupService.selectById(group.getId());
        logger.info(JsonUtils.getObjectMapper().writeValueAsString(group));
        Assert.assertNotNull(group);
    }

    @Test
    public void getDefaultGroupIds() {
        List<Long> ids = groupService.getDefaultGroupIds(1);
        for (Long id : ids) {
            logger.info("id : {}", id);
        }
        Assert.assertNotNull(group);
    }

    @Test
    public void getGroupIds() {
        List<Long> ids = groupRelationService.getGroupIds(1l);
        for (Long id : ids) {
            logger.info("id : {}", id);
        }
        Assert.assertNotNull(ids);
    }

}
