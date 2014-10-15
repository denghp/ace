/**
 * Copyright (c) 2009-2015 http://demi-panda.com
 *
 * Licensed 
 */
package com.ace.console.common.mybatis;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created with ace.
 * User: denghp
 * Date: 11/1/13
 * Time: 10:03 PM
 */
public interface BaseMapper<T,ID extends Serializable> {

    /**
     * 根据ID删除
     * @param id
     * @return
     */
    int delete(ID id);

    /**
     * 删除实体对象
     * @param t
     * @return
     */
    int delete(T t);

    /**
     * 根据ids删除多条role
     * @param params key : ids value: [1,2,3]
     * @return
     */
    int deleteByIds(Map<String, Object> params);

    /**
     * update T 实体对象
     * @param t
     * @return
     */
    int update(T t);

    /**
     * 存储实体对象
     * @param t
     * @return
     */
    int save(T t);

    /**
     * 获取单个实体对象
     * @param id
     * @return
     */
    T getOne(ID id);

    /**
     * 获取集合对象
     * @return
     */
    public List<T> getList();

    /**
     * 分页获取数据
     * @param params
     * @return
     */
    List<T> getPageList(Map<String, Object> params);


    /**
     * 获取总数
     * @return
     */
    int getCount();
}
