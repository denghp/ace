/**
 * Copyright (c) 20095-2013 https://github.com/zhangkaitao
 *
 *
 */


package com.ace.console.service.sys.impl;

import com.ace.console.common.mybatis.BaseMapper;
import com.ace.console.common.support.InjectBaseDependencyHelper;
import com.ace.console.common.utils.Page;
import com.ace.console.exception.AceException;
import com.ace.console.service.GenericService;
import com.ace.core.persistence.mapper.GenericeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @Project_Name: ace-web
 * @File: AbstractService
 * @User: denghp
 * @Date: 11/1/13
 * @Time: 4:47 PM
 */
public abstract class AbstractService<T, ID extends Serializable> implements GenericService<T, ID>, InitializingBean {

    private Logger logger = LoggerFactory.getLogger(AbstractService.class);
    private Class<T> entityClass;

    private GenericeMapper<T, ID> genericeMapper;

    public void setGenericeMapper(GenericeMapper<T, ID> genericeMapper) {
        this.genericeMapper = genericeMapper;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        InjectBaseDependencyHelper.findAndInjectBaseRepositoryDependency(this);

        Assert.notNull(genericeMapper, "BaseRepository required, Class is:" + getClass());

    }

    @Override
    public ID save(T entity) throws AceException {
        return null;
    }

    @Override
    public void delete(T entity) throws AceException {

    }

    @Override
    public void deleteAll(Collection<T> entites) throws AceException {

    }

    @Override
    public void update(T entity) throws AceException {

    }

    @Override
    public T findById(ID id) {
        return genericeMapper.findOne(id);
    }

    @Override
    public List<T> findAll() {
        return null;
    }

    @Override
    public int count() {
        return 0;
    }

    @Override
    public List<T> getPageList(Integer offset, Integer limit) {
        return null;
    }

    @Override
    public int getPageSize(int size) {
        return 0;
    }

    @Override
    public Page<T> page(Map<String, Object> params, Integer pageNum, Integer pageSize) {
        return null;
    }

    @Override
    public boolean exists(ID id) {
        return false;
    }
}
