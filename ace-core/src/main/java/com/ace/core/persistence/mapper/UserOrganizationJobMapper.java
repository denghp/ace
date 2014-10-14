package com.ace.core.persistence.mapper;

import com.ace.core.persistence.entity.UserOrganizationJob;

public interface UserOrganizationJobMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserOrganizationJob record);

    int insertSelective(UserOrganizationJob record);

    UserOrganizationJob selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserOrganizationJob record);

    int updateByPrimaryKey(UserOrganizationJob record);
}