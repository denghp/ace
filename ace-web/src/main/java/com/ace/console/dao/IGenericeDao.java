package com.ace.console.dao;

import com.ace.console.entity.PersistentEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;

/**
 * @Project_Name: ace-parent
 * @File: IGenericeDao
 * (C) Copyright ACE Corporation 2014 All Rights Reserved.
 * @Author: denghp
 * @Date: 10/16/14
 * @Time: 11:28 PM
 * @Description:
 */
public interface IGenericeDao<T extends PersistentEntity> {

    @Insert("insert into ${table} values (#{row.created}, #{row.keyVal},#{row.fieldName},#{row.doubleVal},#{row.longVal},#{row.stringVal})")
    public long save(@Param("table")String table, T entity);
}
