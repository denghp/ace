package com.ace.core.persistence.sys.mapper;

import com.ace.core.persistence.sys.entity.Group;
import com.ace.core.persistence.sys.entity.Resource;

import java.util.List;
import java.util.Map;

/**
 * @Project_Name: ace
 * @File: GroupMapper
 * (C) Copyright ACE Corporation 2014 All Rights Reserved.
 * @Author: denghp
 * @Date: 10/18/14
 * @Time: 4:01 PM
 * @Description:
 */
public interface ResourceMapper extends GenericMapper<Resource, Long> {

    public List<Resource> getAllWithSort(Map<String,Object> params);


    public List<Resource> getChildsByPid(int pid);

}
