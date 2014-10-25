package com.ace.console.service.sys.impl;

import com.ace.console.annotation.BaseComponent;
import com.ace.console.service.sys.GroupRelationService;
import com.ace.core.persistence.sys.entity.GroupRelation;
import com.ace.core.persistence.sys.mapper.GroupRelationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Project_Name: ace-parent
 * @File: GroupRelationServiceImpl
 * (C) Copyright ACE Corporation 2013 All Rights Reserved.
 * @Author: denghp
 * @Date: 14-10-24
 * @Time: 下午9:11
 * @Description:
 */
public class GroupRelationServiceImpl extends GenericeServiceImpl<GroupRelation, Long> implements GroupRelationService {

    private Logger logger = LoggerFactory.getLogger(GroupRelationServiceImpl.class);
    @Resource
    @BaseComponent
    private GroupRelationMapper groupRelationMapper;

    @Override
    public List<Long> getGroupIds(Long userId) {
        if (userId == null) {
            logger.warn("Then userId is null.");
            return null;
        }
        return groupRelationMapper.getGroupIds(userId);
    }
}
