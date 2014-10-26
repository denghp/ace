package com.ace.console.service.sys.impl;

import com.ace.console.bind.annotation.BaseComponent;
import com.ace.console.service.sys.GroupService;
import com.ace.core.persistence.sys.entity.Group;
import com.ace.core.persistence.sys.mapper.GroupMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Project_Name: ace-parent
 * @File: GroupServiceImpl
 * (C) Copyright ACE Corporation 2013 All Rights Reserved.
 * @Author: denghp
 * @Date: 14-10-24
 * @Time: 上午11:45
 * @Description:
 */
@Service
public class GroupServiceImpl extends GenericServiceImpl<Group, Long> implements GroupService {

    @Resource
    @BaseComponent
    private GroupMapper groupMapper;

    @Override
    public List<Long> getDefaultGroupIds(Integer enabled) {

        return groupMapper.getDefaultGroupIds(enabled);
    }
}
