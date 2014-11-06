package com.ace.core.persistence.sys.mapper;

import com.ace.core.persistence.sys.entity.Resources;

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
public interface ResourcesMapper extends GenericMapper<Resources, Long> {

    public List<Resources> getAllWithSort(Map<String,Object> params);


    public List<Resources> getChildsByPid(int pid);

}
