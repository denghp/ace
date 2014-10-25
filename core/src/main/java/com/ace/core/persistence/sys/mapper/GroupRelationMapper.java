package com.ace.core.persistence.sys.mapper;

import com.ace.core.persistence.sys.entity.GroupRelation;

import java.util.List;

/**
 * @Project_Name: ace
 * @File: GroupMapper
 * (C) Copyright ACE Corporation 2014 All Rights Reserved.
 * @Author: denghp
 * @Date: 10/18/14
 * @Time: 4:01 PM
 * @Description:
 */
public interface GroupRelationMapper extends GenericMapper<GroupRelation, Long> {

    /**
     * 根据用户ID获取对应的group ids
     * @param userId
     *          用户ID
     * @return
     *          list group
     */
    public List<Long> getGroupIds(Long userId);

}
