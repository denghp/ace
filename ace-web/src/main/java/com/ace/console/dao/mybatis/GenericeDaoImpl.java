package com.ace.console.dao.mybatis;

import com.ace.console.common.utils.Page;
import com.ace.console.common.utils.PageBean;
import com.ace.console.common.utils.ReflectUtils;
import com.ace.console.dao.GenericeDao;
import com.ace.console.dao.enums.NameSpace;
import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @Project_Name: ace-parent
 * @File: GenericeDaoImpl
 * (C) Copyright ACE Corporation 2014 All Rights Reserved.
 * @Author: denghp
 * @Date: 10/15/14
 * @Time: 10:27 PM
 * @Description:
 */
public class GenericeDaoImpl<T, ID extends Serializable> extends SqlSessionDaoSupport implements GenericeDao<T, ID> {

    private Logger logger = LoggerFactory.getLogger(GenericeDaoImpl.class);

    @Resource(name = "sqlSessionTemplate")
    public void setSuperSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

    private String namespace;

    /**
     * 获取默认SqlMapping命名空间。
     * 使用泛型参数中业务实体类型的全限定名作为默认的命名空间。
     * 如果实际应用中需要特殊的命名空间，可由子类重写该方法实现自己的命名空间规则。
     * @return 返回命名空间字符串
     */
    protected String getNamespace() {
        Class<T> clazz = ReflectUtils.getClassGenricType(this.getClass());
        String nameSpace = clazz.getName();
        return nameSpace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    @Override
    public long save(T entity) {
        try {
           return (long)getSqlSession().insert(namespace + "." + NameSpace.SQLID_INSERT.getNamespace(),entity);
        } catch (Exception e) {
            logger.error("save entity error : ", e);
        }
        return 0l;
    }

    @Override
    public int delete(ID id) {
        try {
            return getSqlSession().delete(namespace + "." + NameSpace.SQLID_DELETE, id);
        } catch (Exception e) {
            logger.error("delete entity error : ", e);
        }
        return 0;
    }


    @Override
    public int deleteBatch(List<ID> idList) {
        try {
            return getSqlSession().delete(namespace + "." + NameSpace.SQLID_DELETE_BATCH, idList);
        } catch (Exception e) {
            logger.error("deleteBatch error : ", e);
            return 0;
        }
    }

    @Override
    public int insertBatch(Collection<T> entites) {
        try {
            return getSqlSession().insert(namespace + "." + NameSpace.SQLID_INSERT_BATCH, entites);
        } catch (Exception e) {
            logger.error("insertBatch error : ", e);
            return 0;
        }
    }

    @Override
    public int updateBatch(Collection<T> entites) {
        try {
            return getSqlSession().update(namespace + "." + NameSpace.SQLID_UPDATE_BATCH, entites);
        } catch (Exception e) {
            logger.error("updateBatch error : ", e);
            return 0;
        }
    }

    @Override
    public int update(T entity) {
        try {
            return getSqlSession().update(namespace + "." + NameSpace.SQLID_UPDATE, entity);
        } catch (Exception e) {
            logger.error("update error : ", e);
            return 0;
        }
    }

    @Override
    public T findById(ID id) {
        try {
            return getSqlSession().selectOne(namespace + "." + NameSpace.SQLID_SELECT_ID, id);
        } catch (Exception e) {
            logger.error("findById error : ", e);
            return null;
        }
    }

    @Override
    public List<T> findAll() {
        try {
            return getSqlSession().selectList(namespace + "." + NameSpace.SQLID_SELECT);
        } catch (Exception e) {
            logger.error("findAll error : ", e);
            return null;
        }
    }

    @Override
    public int count() {
        try {
            return getSqlSession().selectOne(namespace + "." + NameSpace.SQLID_COUNT);
        } catch (Exception e) {
            logger.error("count error : ", e);
            return 0;
        }
    }

    @Override
    public int count(Object param) {
        int result = 0;
        try {
            result = getSqlSession().selectOne(namespace + "." + NameSpace.SQLID_COUNT_PARAM, param);
        } catch (Exception e) {
            logger.error("count param error : ", e);
        }
        return result;
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
        try {
            PageBean pageBean = new PageBean(pageNum, pageSize);

            RowBounds rowBounds = new RowBounds((pageBean.getPage() -1) * pageBean.getPageSize(), pageBean.getPageSize());



            List<T> resultList = getSqlSession().selectList(namespace + "." + NameSpace.SQLID_SELECT_PARAM, params, rowBounds);
            int totalSize = count(params);

            Page<T> pageResult = new Page<T>();
            pageResult.setPageBean(pageBean);
            pageResult.setResult(resultList);

            return pageResult;

        } catch (Exception e) {
            logger.error("page error : ", e);
            return null;
        }
    }

    @Override
    public boolean exists(ID id) {
        try {
            return getSqlSession().selectOne(namespace + "." + NameSpace.SQLID_SELECT_ID, id);
        } catch (Exception e) {
            logger.error("findAll error : ", e);
        }
        return false;
    }
}
