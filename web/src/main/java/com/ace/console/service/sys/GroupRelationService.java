package com.ace.console.service.sys;

import com.ace.console.service.GenericService;
import com.ace.core.persistence.sys.entity.Group;
import com.ace.core.persistence.sys.entity.GroupRelation;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Project_Name: ace-parent
 * @File: GroupService
 * (C) Copyright ACE Corporation 2013 All Rights Reserved.
 * @Author: denghp
 * @Date: 14-10-24
 * @Time: 上午11:37
 * @Description:
 */
public interface GroupRelationService extends GenericService<GroupRelation, Long> {

    /**
     * 根據userId查詢組Ids
     * @param userId
     *          有效: 1- 表示有效 0 - 表示无效
     * @return
     *          list group Id 集合
     */
    public List<Long> getGroupIds(Long userId);

}
