/**
 * Copyright (c) 20095-2013 https://github.com/zhangkaitao
 *
 *
 */


package com.ace.console.service.sys.impl;

import com.ace.commons.json.JsonUtils;
import com.ace.console.common.support.InjectBaseDependencyHelper;
import com.ace.console.exception.AceException;
import com.ace.console.service.GenericService;
import com.ace.core.page.Page;
import com.ace.core.persistence.sys.mapper.GenericMapper;
import com.ace.core.utils.ReflectUtils;
import com.google.code.ssm.api.ParameterValueKeyProvider;
import com.google.code.ssm.api.ReadThroughSingleCache;
import com.google.code.ssm.api.ReturnValueKeyProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @Project_Name: ace-web
 * @File: GenericServiceImpl
 * @User: denghp
 * @Date: 11/1/13
 * @Time: 4:47 PM
 */
public abstract class GenericServiceImpl<T, ID extends Serializable> implements GenericService<T, ID>, InitializingBean {

    private Logger logger = LoggerFactory.getLogger(GenericServiceImpl.class);

    private GenericMapper<T, ID> genericeMapper;

    public void setGenericeMapper(GenericMapper<T, ID> genericeMapper) {
        this.genericeMapper = genericeMapper;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        InjectBaseDependencyHelper.findAndInjectBaseRepositoryDependency(this);

        Assert.notNull(genericeMapper, "BaseRepository required, Class is:" + getClass());

    }

    @Override
    public T save(T entity) throws AceException {

        try {
            genericeMapper.insert(entity);
            logger.debug("entity : {}", JsonUtils.getObjectMapper().writeValueAsString(entity));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return entity;
    }

    @Override
    public void delete(T entity) throws AceException {

    }

    @Override
    public void deleteList(List<ID> ids) throws AceException {
        genericeMapper.deleteBatch(ids);
    }

    @Override
    public void update(T entity) throws AceException {
        genericeMapper.update(entity);
    }

    @Override
    public T selectById(ID id) {
        if (id == null) {
            logger.error("id is null.");
            return null;
        }
        return genericeMapper.selectById(id);
    }

    @Override
    public List<T> selectList(String property, Object value) {
        return genericeMapper.selectList(property, value);
    }

    @Override
    public T selectOne(String property, Object value) {
        return genericeMapper.selectOne(property, value);
    }

    @Override
    public List<T> selectAll() {
        return genericeMapper.selectAll();
    }

    @Override
    public int count() {
        return genericeMapper.count();
    }

    @Override
    public List<T> getPageList(Integer offset, Integer limit) {
        return null;
    }

    @Override
    public Page<T> page(Map<String, Object> params, Integer pageNum, Integer pageSize) {
        return genericeMapper.page(params,pageNum, pageSize);
    }

    @Override
    public boolean exists(ID id) {
        return genericeMapper.exists(id);
    }

    /**
     * 获取当前实体对象class
     * @return
     */
    public Class<T> getEntityClass() {

        return ReflectUtils.getClassGenricType(getClass());
    }
}
