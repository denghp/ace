package com.ace.console.service;


import com.ace.console.exception.AceException;
import com.ace.core.page.Page;
import com.ace.core.persistence.mapper.GenericeMapper;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Project_Name: ace
 * File: GenericService
 * (C) Copyright ACE Corporation 2013 All Rights Reserved.
 * @Author: denghp
 * @Date: 10/15/14
 * @Time: 2:47 PM
 * @Description:
 *      基于范型实现通用的服务接口
 *
 * @param <T>
 *           持久化的实体Bean
 * @param <ID>
 *           实体Bean的ID
 */
public interface GenericService<T, ID extends Serializable> {
    /**
     * 设置基础baseMapper
     * @param genericeMapper
     */
    public void setGenericeMapper(GenericeMapper<T, ID> genericeMapper);
    /**
     * 保存单个实体
     *
     * @param entity
     *              实体Bean
     * @return
     *              返回保存实体的ID
     */
    public T save(T entity) throws AceException;


    /**
     * 删除实体
     *
     * @param entity
     *          实体Bean
     */
    public void delete(T entity) throws AceException;

    /**
     *
     *  删除实体集合
     *
     * @param ids
     *         需要删除的实体的集合
     */
    public void deleteAll(List<ID> ids) throws AceException;

    /**
     *  更新实体
     * @param entity 实体
     * @return
     * @throws AceException
     */
    public void update(T entity) throws AceException;

    /**
     * 按照主键查询
     *
     * @param id
     *         主键
     * @return
     *         返回查询的实体对象
     */
    public T findById(ID id);

    /**
     *
     *  查询实体列表
     *
     * @return
     */
    public List<T> findAll();

    /**
     * 统计实体总数
     *
     * @return 实体总数
     */
    public int count();

    /**
     *
     *  根据给定的HQL语句进行分页查询
     *
     * @param offset
     *          查询的起始位置
     * @param limit
     *          查询返回的数量
     * @return
     *          返回集合实体列表
     */
    public List<T> getPageList(Integer offset, Integer limit);

    /**
     * <p>
     * 根据每页记录的数量,计算出总的分页数
     * </p>
     *
     * @param size 每页记录的数量
     * @return 分页总数
     */
    public int getPageSize(int size);

    /**
     * <p/>
     * 根据给定的页码进行分页查找,这是纯Hibernate分页.
     * <p/>
     * @param params
     *                  查詢需要的參數,以key-value形式組成
     * @param pageNum
     *                  查詢的頁數
     * @param pageSize
     *                  每頁返回的數量
     * @return
     */
    public Page<T> page(Map<String, Object> params, Integer pageNum, Integer pageSize);

    /**
     * 查询实体是否存在
     * @param id
     * @return 返回true|false
     */
    public boolean exists(ID id);


}
