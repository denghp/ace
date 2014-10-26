/**
 * Copyright (c) 2009-2018 https://github.com/denghp
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.ace.core.persistence.sys.mapper.impl;

import com.ace.core.persistence.sys.entity.Resource;
import com.ace.core.persistence.sys.mapper.ResourceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author: denghp
 * @Date: 10/25/14 10:39 PM
 * @Description:
 */
@Repository
public class ResourceMapperImp extends GenericMapperImpl<Resource, Long> implements ResourceMapper {
    private Logger logger = LoggerFactory.getLogger(UserMapperImpl.class);
    public enum ResourceRdbOperation {

        GET_ALL_WITH_SORT(".getAllWithSort"),
        GET_CHILDS_BY_PID(".getChildsByPid");

        private String value;

        private ResourceRdbOperation(String value) {
            this.value = value;
        }

        public String value() {
            return this.value;
        }
    }

    @Override
    public List<Resource> getAllWithSort(Map<String, Object> params) {
        return getSqlSession().selectList(getNamespace() + ResourceRdbOperation.GET_ALL_WITH_SORT.value(), params);
    }

    @Override
    public List<Resource> getChildsByPid(int pid) {
        return getSqlSession().selectList(getNamespace() + ResourceRdbOperation.GET_CHILDS_BY_PID.value(), pid);
    }
}
