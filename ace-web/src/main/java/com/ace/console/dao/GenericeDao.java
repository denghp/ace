package com.ace.console.dao;

import com.ace.console.common.utils.Page;
import com.ace.console.exception.AceException;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @Project_Name: ace-parent
 * @File: GenericeDao
 * (C) Copyright ACE Corporation 2014 All Rights Reserved.
 * @Author: denghp
 * @Date: 10/15/14
 * @Time: 9:56 PM
 * @Description:
 */
public interface GenericeDao<T, ID extends Serializable>  {

    /**
     * 保存单个实体
     *
     * @param entity
     *              实体Bean
     * @return
     *              返回保存实体的ID
     */
    public long save(T entity) ;


    /**
     * 删除实体
     *
     * @param id
     *          实体Bean
     */
    public int delete(ID id) ;

    /**
     * 批量删除
     * @param idList
     */
    public int deleteBatch(final List<ID> idList);

    /**
     * 批量插入
     * @param entites
     *       需要插入的实体集合
     */
    public int insertBatch(final Collection<T> entites);

    /**
     * 批量修改
     * @param entites
     *        需要插入的实体集合
     */
    public int updateBatch(final Collection<T> entites);

    /**
     *  更新实体
     * @param entity 实体
     * @return
     */
    public int update(T entity) ;

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
     * 查询符合条件的记录数
     * @param param 查询条件参数，包括WHERE条件（其他参数内容不起作用）。此参数设置为null，则相当于count()
     * @return
     */
    public int count(Object param);

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
