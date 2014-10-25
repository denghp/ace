package com.ace.core.persistence.sys.mapper.impl;

import com.ace.core.persistence.sys.entity.GroupRelation;
import com.ace.core.persistence.sys.mapper.GenericeMapper;
import com.ace.core.persistence.sys.mapper.GroupRelationMapper;

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
public class GroupRelationMapperImpl extends GenericeMapperImpl<GroupRelation, Long> implements GroupRelationMapper {

    public enum RdbOperation {

        GET_GROUP_IDS(".getGroupIds");

        private String value;

        private RdbOperation(String value) {
            this.value = value;
        }

        public String value() {
            return this.value;
        }
    }

    @Override
    public List<Long> getGroupIds(Long userId) {
        return getSqlSession().selectList(getNamespace() + RdbOperation.GET_GROUP_IDS.value(), userId);
    }
}
