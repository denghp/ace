package com.ace.console.service;


import com.ace.console.exception.AceException;
import com.ace.core.paginator.domain.PageList;
import com.ace.core.persistence.sys.mapper.GenericMapper;

import java.io.Serializable;
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
    public void setGenericeMapper(GenericMapper<T, ID> genericeMapper);
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
     * @param id
     *          实体对的主键ID
     */
    public void delete(ID id) throws AceException;

    /**
     *
     *  删除实体集合
     *
     * @param ids
     *         需要删除的实体的集合
     */
    public void deleteList(List<ID> ids) throws AceException;

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
    public T selectById(ID id);


    /**
     * 根据任意属性和属性值进行对象查询，如果返回多个对象，将抛出异常
     *
     * @param property
     *            进行对象匹配的属性
     * @param value
     *            进行对象匹配的属性值
     * @return 返回泛型参数类型对象
     */
    public T selectOne(String property, Object value);

    /**
     * 根据任意（单一）属性和属性值进行集合查询
     *
     * @param property
     *            进行对象匹配的属性
     * @param value
     *            进行对象匹配的属性值
     * @return 返回泛型参数类型的对象集合
     */
    public List<T> selectList(String property, Object value);

    /**
     * 根据传入的泛型参数类型查询该类型对应表中的所有数据，返回一个集合对象
     *
     * @return 返回泛型参数类型的对象集合
     */
    public List<T> selectAll();


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
     * @param pageNum
     *          查询的页码
     * @param pageSize
     *          查询返回的数量
     * @return
     *          返回集合实体列表
     */
    public List<T> getPageList(Integer pageNum, Integer pageSize);

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
    public PageList<T> page(Map<String, Object> params, Integer pageNum, Integer pageSize);

    /**
     * 查询实体是否存在
     * @param id
     * @return 返回true|false
     */
    public boolean exists(ID id);


}
