package com.ace.core.persistence.mapper;

import com.ace.core.persistence.entity.UserRoles;

public interface UserRolesMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserRoles record);

    int insertSelective(UserRoles record);

    UserRoles selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserRoles record);

    int updateByPrimaryKey(UserRoles record);
}