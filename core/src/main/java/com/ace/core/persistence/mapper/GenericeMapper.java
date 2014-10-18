package com.ace.core.persistence.mapper;

import com.ace.core.page.Page;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @Project_Name: ace-core
 * @File: GenericeMapper
 * (C) Copyright ACE Corporation 2014 All Rights Reserved.
 * @Author: denghp
 * @Date: 10/15/14
 * @Time: 9:56 PM
 * @Description:
 */
public interface GenericeMapper<T, ID extends Serializable>  {

    /**
     * 保存单个实体,如果需要保存多个实体集合{@link #insertBatch(java.util.Collection)}
     *
     * @param entity
     *              实体Bean
     * @return
     *              返回保存实体
     */
    public Long insert(T entity);


    /**
     * 批量插入
     * @param entites
     *       需要插入的实体集合
     */
    public int insertBatch(final Collection<T> entites);

    /**
     * 删除实体
     *
     * @param id
     *          实体Bean
     */
    public int delete(ID id) ;

    /**
     * 根据条件集合删除对象
     *
     * @param condition
     */
    public void deleteByCondition(Map<String, Object> condition);

    /**
     * 批量删除
     * @param idList
     */
    public int deleteBatch(final List<ID> idList);


    /**
     *  <p>
     *      更更新对象,如果属性为空,则不会进行对应的属性值更新,
     *      如果有属性要更新为null,参考{@link #updateNull(Object)}
     *      需要更新多个实体集合,参考 {@link #updateBatch(java.util.Collection)}
     *  </p>
     *
     * @param entity 实体
     * @return
     */
    public int update(T entity) ;

    /**
     * 更新对象,如果属性为空，会进行对应的属性值更新,如果有属性不想更新为null，请参看{@link #update(Object)}
     *
     * @param entity
     */
    public void updateNull(T entity);

    /**
     * 批量修改
     * @param entites
     *        需要插入的实体集合
     */
    public int updateBatch(final Collection<T> entites);



    /**
     * 按照主键查询
     *
     * @param id
     *         主键
     * @return
     *         返回查询的实体对象
     */
    public T findOne(ID id);


    /**
     * 根据条件集合进行指定类型单一对象查询
     *
     * @param condition
     *            进行查询的条件集合
     * @return 返回泛型参数类型的对象，如何取到泛型类型参数，请参看{@link #getEntityClass()}，
     */
    public T findOne(Map<String, Object> condition);

    /**
     *
     *  查询实体列表
     *
     * @return
     */
    public List<T> findAll();

    /**
     * 根据条件集合进行指定类型对象集合查询
     *
     * @param condition
     *            进行查询的条件集合
     * @return 返回泛型参数类型的对象集合，如何取到泛型类型参数，请参看{@link #getEntityClass()}
     */
    public List<T> queryList(Map<String, Object> condition,String orderBy,String sortBy);


    /**
     * 统计实体总数
     *
     * @return 实体总数
     */
    public int count();

    /**
     * 查询符合条件的记录数
     * @param condition
     *      查询条件参数，包括WHERE条件（其他参数内容不起作用）,此参数设置为null，则相当于count()
     * @return
     */
    public int count(Map<String, Object> condition);

    /**
     * <p>
     *      更新或保存，涉及到Mabatis使用的bean只是一个简单的值对象，不能进行id的注解，
     *      不知道哪个是主键，所以，必须同时指定t的主键值
     * </p>
     * @param t
     *            要更新或保存的对象
     * @param id
     *            要更新或保存的对象的id
     * @return 返回更新或保存的对象。
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws SecurityException
     * @throws IllegalArgumentException
     */
    public T updateOrSave(T t, ID id);

    /**
     * <p/>
     * 根据给定的页码进行分页查找,这是纯Hibernate分页.
     * <p/>
     * @param condition
     *                  查詢需要的參數,以key-value形式組成
     * @param pageNum
     *                  查詢的頁數
     * @param pageSize
     *                  每頁返回的數量
     * @return
     */
    public Page<T> page(Map<String, Object> condition, Integer pageNum, Integer pageSize);

    /**
     * 查询实体是否存在
     * @param id
     * @return 返回true|false
     */
    public boolean exists(ID id);

    /**
     * 取得泛型类型
     *
     * @return
     */
    public Class<T> getEntityClass();
}
