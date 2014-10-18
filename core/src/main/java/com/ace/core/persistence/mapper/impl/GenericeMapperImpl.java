package com.ace.core.persistence.mapper.impl;

import com.ace.core.page.Page;
import com.ace.core.page.PageBean;
import com.ace.core.persistence.enums.RdbOperation;
import com.ace.core.persistence.mapper.GenericeMapper;
import com.ace.core.utils.ReflectUtils;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Project_Name: ace
 * @File: GenericeMapperImpl
 * (C) Copyright ACE Corporation 2014 All Rights Reserved.
 * @Author: denghp
 * @Date: 10/18/14
 * @Time: 1:38 PM
 * @Description:
 *
 */
@Repository
public class GenericeMapperImpl<T, ID extends Serializable> extends SqlSessionDaoSupport implements GenericeMapper<T, ID> {

    public static Logger logger = LoggerFactory.getLogger(GenericeMapperImpl.class);

    private String namespace;

    @Resource(name = "sqlSessionFactory")
    public void setSuperSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    public String getNamespace() {
        Class<T> clazz = ReflectUtils.getClassGenricType(this.getClass());
        String nameSpace = clazz.getName();
        return nameSpace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    @Override
    public Long insert(T entity) {
        int id = getSqlSession().insert(getNamespace() + RdbOperation.INSERT, entity);
        logger.info("insert entity return id : {}", id);
//        try {
//            Field[] fileds = entity.getClass().getDeclaredFields();
//            for (Field field : fileds) {
//                if (field.getName().equals("id")) {
//                    Method setIdMethod = entity.getClass().getDeclaredMethod("setId", Long.class);
//                    setIdMethod.invoke(entity, id);
//                }
//            }
//        } catch (NoSuchMethodException ex) {
//            ex.printStackTrace();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
        return 0l;
    }

    @Override
    public int insertBatch(Collection<T> entites) {
        return getSqlSession().insert(getNamespace() + RdbOperation.INSERT_BATCH, entites);

    }

    @Override
    public int delete(ID id) {
        return getSqlSession().delete(getNamespace() + RdbOperation.DELETE, id);
    }

    @Override
    public void deleteByCondition(Map<String, Object> condition) {
        getSqlSession().delete(getNamespace() + RdbOperation.DELETE_CONDITION, condition);
    }

    @Override
    public int deleteBatch(List<ID> idList) {
        return getSqlSession().delete(getNamespace() + RdbOperation.DELETE_BATCH, idList);
    }

    @Override
    public int update(T entity) {
        return getSqlSession().update(getNamespace() + RdbOperation.UPDATE, entity);
    }

    @Override
    public void updateNull(T entity) {
        getSqlSession().update(getNamespace() + RdbOperation.UPDATE, entity);
    }

    @Override
    public int updateBatch(Collection<T> entites) {
        return getSqlSession().update(getNamespace() + RdbOperation.UPDATE_BATCH, entites);
    }

    @Override
    public T findOne(ID id) {
        return getSqlSession().selectOne(getNamespace() + RdbOperation.SELECT_ID, id);
    }

    @Override
    public T findOne(Map<String, Object> condition) {
        return getSqlSession().selectOne(getNamespace() + RdbOperation.SELECT_CONDITION, condition);
    }

    @Override
    public List<T> findAll() {
        return null;
    }

    @Override
    public List<T> queryList(Map<String, Object> condition, String orderBy, String sortBy) {

        if(condition==null){
            condition=new HashMap<String, Object>();
            condition.put("orderBy",orderBy );
            condition.put("sortBy",sortBy );
        }

        return getSqlSession().selectList(
                getNamespace() + RdbOperation.SELECT_LIST, condition);

    }

    @Override
    public int count() {
        return count(null);
    }

    @Override
    public int count(Map<String, Object> condition) {
        return getSqlSession().selectOne(
                getNamespace() + RdbOperation.SELECT, condition);
    }

    @Override
    public T updateOrSave(T t, ID id) {
        if (null != findOne(id)) {
            update(t);
        } else {
            //TODO : INSERT
            //return insert(t);
            return null;
        }

        return t;
    }

    @Override
    public Page<T> page(Map<String, Object> condition, Integer pageNum, Integer pageSize) {
        //获取总数,初始化pagebean
        PageBean pageBean = new PageBean(pageNum, pageSize, count(condition));

        try {

            if (condition == null) {
                condition = new HashMap<String, Object>();
            }

            RowBounds rowBounds = new RowBounds((pageBean.getPage() -1) * pageBean.getPageSize(), pageBean.getPageSize());

            List<T> resultList = this.getSqlSession().selectList(
                    getNamespace() + RdbOperation.SELECT_LIST, condition,rowBounds);

            Page<T> pageResult = new Page<T>();
            pageResult.setPageBean(pageBean);
            pageResult.setResult(resultList);
            return pageResult;
        } catch (Exception ex) {
            logger.error("findList " + getNamespace() + "failed : ", ex);
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
