package com.ace.core.persistence.sys.mapper.impl;

import com.ace.core.paginator.domain.PageBounds;
import com.ace.core.paginator.domain.PageList;
import com.ace.core.persistence.sys.enums.RdbOperation;
import com.ace.core.persistence.sys.mapper.GenericMapper;
import com.ace.core.utils.ReflectUtils;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Project_Name: ace
 * @File: GenericMapperImpl
 * (C) Copyright ACE Corporation 2014 All Rights Reserved.
 * @Author: denghp
 * @Date: 10/18/14
 * @Time: 1:38 PM
 * @Description:
 *
 */
@Repository
public class GenericMapperImpl<T, ID extends Serializable> extends SqlSessionDaoSupport implements GenericMapper<T, ID> {

    private Logger logger = LoggerFactory.getLogger(GenericMapperImpl.class);
    public java.util.logging.Logger log = java.util.logging.Logger.getLogger(getClass().getName());
    private String namespace;

    @Resource(name = "sqlSessionFactory")
    public void setSuperSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    public String getNamespace() {
        //Class<T> clazz = ReflectUtils.getClassGenricType(this.getClass());
        Class<T> clazz = ReflectUtils.getGenericInterface(getClass(), 0);
        String nameSpace = clazz.getName();
        return nameSpace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    @Override
    public int insert(T entity) {
        return getSqlSession().insert(getNamespace() + RdbOperation.INSERT_SELECTIVE.value(), entity);
    }

    @Override
    public int insertBatch(Collection<T> entites) {
        return getSqlSession().insert(getNamespace() + RdbOperation.INSERT_BATCH.value(), entites);

    }

    @Override
    public int delete(ID id) {
        return getSqlSession().delete(getNamespace() + RdbOperation.DELETE_BY_PRIMARY_KEY.value(), id);
    }

    @Override
    public void deleteByCondition(Map<String, Object> condition) {
        getSqlSession().delete(getNamespace() + RdbOperation.DELETE_CONDITION.value(), condition);
    }

    @Override
    public int deleteBatch(List<ID> idList) {
        return getSqlSession().delete(getNamespace() + RdbOperation.DELETE_BATCH.value(), idList);
    }

    @Override
    public int update(T entity) {
        return getSqlSession().update(getNamespace() + RdbOperation.UPDATE_BY_PRIMARY_KEY_SELECTIVE.value(), entity);
    }

    @Override
    public void updateNull(T entity) {
        getSqlSession().update(getNamespace() + RdbOperation.UPDATE_BY_PRIMARY_KEY_SELECTIVE.value(), entity);
    }

    @Override
    public int updateBatch(Collection<T> entites) {
        return getSqlSession().update(getNamespace() + RdbOperation.UPDATE_BATCH.value(), entites);
    }

    @Override
    public T selectById(ID id) {
        String namespace = getNamespace() + RdbOperation.SELECT_BY_PRIMARY_KEY.value();
        logger.info("----- {} ,findOne Id : {} ", namespace,id);
        return getSqlSession().selectOne(namespace, id);
    }

    @Override
    public T selectOne(String property, Object value) {
        Map<String, Object> condition = new HashMap<String, Object>();
        condition.put(property, value);
        return getSqlSession().selectOne(getNamespace() + RdbOperation.SELECT_BY_PRIMARY_KEY.value(), condition);
    }

    @Override
    public List<T> selectList(String property, Object value) {
        Map<String, Object> condition = new HashMap<String, Object>();
        condition.put(property, value);
        return getSqlSession().selectList(getNamespace() + RdbOperation.SELECT_LIST, condition);
    }

    @Override
    public List<T> selectAll() {
        return getSqlSession().selectList(getNamespace() + RdbOperation.SELECT_ALL.value());
    }

    @Override
    public List<T> queryList(Map<String, Object> condition, String orderBy, String sortBy) {

        if(condition==null){
            condition=new HashMap<String, Object>();
            condition.put("orderBy",orderBy );
            condition.put("sortBy",sortBy );
        }

        return getSqlSession().selectList(
                getNamespace() + RdbOperation.SELECT_LIST.value(), condition);

    }

    @Override
    public int count() {
        return count(null);
    }

    @Override
    public int count(Map<String, Object> condition) {
        return getSqlSession().selectOne(
                getNamespace() + RdbOperation.COUNT.value(), condition);
    }

    @Override
    public T updateOrSave(T t, ID id) {
        if (null != selectById(id)) {
            update(t);
        } else {
            //TODO : INSERT_SELECTIVE
            //return insert(t);
            return null;
        }

        return t;
    }

    @Override
    public PageList<T> page(Map<String, Object> condition, Integer pageNum, Integer pageSize) {
        //获取总数,初始化pagebean
        PageBounds pageBounds = new PageBounds(pageNum, pageSize);
        try {

            if (condition == null) {
                condition = new HashMap<String, Object>();
            }

            logger.debug(pageBounds.toString());
            List<T> resultList = this.getSqlSession().selectList(
                    getNamespace() + RdbOperation.SELECT_LIST.value(), condition, pageBounds);

            //获得结果集条总数
            return (PageList)resultList;
        } catch (Exception ex) {
            logger.error("page " + getNamespace() + "failed : ", ex);
        }

        return null;
    }

    @Override
    public boolean exists(ID id) {
        return false;
    }

    @Override
    public Class<T> getEntityClass() {

        return ReflectUtils.getClassGenricType(getClass());
    }
}
