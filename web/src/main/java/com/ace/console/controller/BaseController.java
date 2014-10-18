package com.ace.console.controller;

import com.ace.console.common.utils.ReflectUtils;
import com.ace.console.service.GenericService;
import org.springframework.beans.factory.InitializingBean;

import java.io.Serializable;

/**
 * @Project_Name: ace-parent
 * @File: BaseController
 * (C) Copyright ACE Corporation 2014 All Rights Reserved.
 * @Author: denghp
 * @Date: 10/15/14
 * @Time: 9:34 PM
 * @Description:
 */
public abstract class BaseController<M, ID extends Serializable> implements InitializingBean {

    protected GenericService<M,ID> genericService;
    /**
     * 实体类型
     */
    protected final Class<M> entityClass;

    private String viewPrefix;


    protected BaseController() {
        //通过反射获取相应的实体类对象
        this.entityClass = ReflectUtils.getClassGenricType(getClass());
        //setViewPrefix(defaultViewPrefix());
    }

    /**
     * 设置基础service
     *
     * @param genericService
     */
    public void setGenericService(GenericService<M,ID> genericService) {
        this.genericService = genericService;
    }

    /**
     * 实例化Model
     * @return
     */
    protected M newModel() {
        try {
            return entityClass.newInstance();
        } catch (Exception e) {
            throw new IllegalStateException("can not instantiated model : " + this.entityClass, e);
        }
    }
}
