package com.ace.core.persistence.sys.mapper.impl;

import com.ace.core.persistence.sys.entity.Group;
import com.ace.core.persistence.sys.mapper.GroupMapper;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Project_Name: ace-parent
 * @File: GroupMapperImpl
 * (C) Copyright ACE Corporation 2013 All Rights Reserved.
 * @Author: denghp
 * @Date: 14-10-24
 * @Time: 下午12:03
 * @Description:
 */
@Repository
public class GroupMapperImpl extends GenericMapperImpl<Group, Long> implements GroupMapper {

    public enum RdbOperation {

        GET_DEFAULT_GROUPIDS(".getDefaultGroupIds");

        private String value;

        private RdbOperation(String value) {
            this.value = value;
        }

        public String value() {
            return this.value;
        }
    }


    @Override
    public List<Long> getDefaultGroupIds(Integer enabled) {
        Map<String, Object>  params = Maps.newHashMap();
        params.put("enabled", enabled);
        return getSqlSession().selectList(getNamespace() + RdbOperation.GET_DEFAULT_GROUPIDS.value(), params);
    }
}
