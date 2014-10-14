package com.ace.core.persistence.mapper;

import com.ace.core.persistence.entity.RoleResourcePermission;

public interface RoleResourcePermissionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RoleResourcePermission record);

    int insertSelective(RoleResourcePermission record);

    RoleResourcePermission selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RoleResourcePermission record);

    int updateByPrimaryKey(RoleResourcePermission record);
}