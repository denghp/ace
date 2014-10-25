package com.ace.console.service.sys;

import com.ace.console.service.GenericService;
import com.ace.core.persistence.sys.entity.Group;
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
public interface GroupService extends GenericService<Group, Long> {

    /**
     * 查询默认的组Id集合
     * @param enabled
     *          有效: 1- 表示有效 0 - 表示无效
     * @return
     *          list group
     */
    public List<Long> getDefaultGroupIds(Integer enabled);

}
