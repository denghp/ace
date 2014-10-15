/**
 * Copyright (c) 20095-2013 https://github.com/zhangkaitao
 *
 *
 */


package com.ace.console.service.sys.impl;

import com.ace.console.common.support.InjectBaseDependencyHelper;
import com.ace.console.common.utils.Page;
import com.ace.console.dao.GenericeDao;
import com.ace.console.exception.AceException;
import com.ace.console.service.GenericService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashMap;
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

    private GenericeDao<T, ID> genericeDao;

    public void setGenericeDao(GenericeDao<T, ID> genericeDao) {
        this.genericeDao = genericeDao;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        InjectBaseDependencyHelper.findAndInjectBaseRepositoryDependency(this);

        Assert.notNull(genericeDao, "BaseRepository required, Class is:" + getClass());

    }

    public static enum RdbParams {

        MODIFY_TIME("modifyTime"),
        OFFSET("offset"),
        LIST("list"),
        ROWS("rows"),
        LIMIT("limit"),
        VALID_FLAG("valid_flag");
        private String value;

        private RdbParams(String value) {
            this.value = value;
        }

        public String value() {
            return this.value;
        }
    }

    public static enum RdbOperation {

        GET_LIST_PAGES("getListPages");
        private String value;

        private RdbOperation(String value) {
            this.value = value;
        }

        public String value() {
            return this.value;
        }
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
        return null;
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
