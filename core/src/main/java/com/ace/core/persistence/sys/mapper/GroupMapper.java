package com.ace.core.persistence.sys.mapper;

import com.ace.core.persistence.sys.entity.Group;

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
public interface GroupMapper extends GenericeMapper<Group, Long> {

    /**
     * 查询默认的组Id集合
     * @param enabled
     *          有效: 1- 表示有效 0 - 表示无效
     * @return
     *          list group
     */
    public List<Long> getDefaultGroupIds(Integer enabled);

}
