package com.ace.console.service.sys.impl;

import com.ace.console.bind.annotation.BaseComponent;
import com.ace.console.service.sys.GroupRelationService;
import com.ace.console.utils.Constants;
import com.ace.core.persistence.sys.entity.GroupRelation;
import com.ace.core.persistence.sys.mapper.GroupRelationMapper;
import com.google.code.ssm.api.ParameterValueKeyProvider;
import com.google.code.ssm.api.ReadThroughSingleCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

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
@Service
public class GroupRelationServiceImpl extends GenericServiceImpl<GroupRelation, Long> implements GroupRelationService {

    private Logger logger = LoggerFactory.getLogger(GroupRelationServiceImpl.class);

    @Resource
    @BaseComponent
    private GroupRelationMapper groupRelationMapper;

    @Override
    @ReadThroughSingleCache(namespace = Constants.DEFAULT_PROJECT_NAME + ":group:getGroupIds", expiration = 600)
    public List<Long> getGroupIds(@ParameterValueKeyProvider Long userId) {
        if (userId == null) {
            logger.warn("Then userId is null.");
            return null;
        }
        return groupRelationMapper.getGroupIds(userId);
    }
}
