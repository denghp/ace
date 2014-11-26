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
import com.ace.core.paginator.domain.PageList;
import com.ace.core.persistence.sys.mapper.GenericMapper;
import com.ace.core.utils.ReflectUtils;
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

    private Logger LOGGER = LoggerFactory.getLogger(GenericServiceImpl.class);

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
            LOGGER.debug("entity : {}", JsonUtils.getObjectMapper().writeValueAsString(entity));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return entity;
    }

    @Override
    public void delete(ID id) throws AceException {
        if (id == null) {
            LOGGER.warn("delete object error , the id is empty.");
            return;
        }
        genericeMapper.delete(id);
    }

    @Override
    public void deleteList(List<ID> ids) throws AceException {
        if (ids == null || ids.size() <= 0) {
            LOGGER.warn("delete objects error , the ids is empty.");
            return;
        }
        genericeMapper.deleteBatch(ids);
    }

    @Override
    public void update(T entity) throws AceException {
        if (entity == null) {
            LOGGER.warn("update object failed, the entity is null.");
            return;
        }
        genericeMapper.update(entity);
    }


    @Override
    public T selectById(ID id) {
        if (id == null) {
            LOGGER.error("id is null.");
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
    public List<T> getPageList(Integer pageNum, Integer pageSize) {
        return page(null, pageNum, pageSize);
    }

    @Override
    public PageList<T> page(Map<String, Object> params, Integer pageNum, Integer pageSize) {
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
