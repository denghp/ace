package com.ace.core.persistence.mapper;

import com.ace.core.persistence.entity.GroupRelation;

public interface GroupRelationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GroupRelation record);

    int insertSelective(GroupRelation record);

    GroupRelation selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GroupRelation record);

    int updateByPrimaryKey(GroupRelation record);
}