package com.ace.console.controller;

import com.ace.console.common.support.InjectBaseDependencyHelper;
import com.ace.console.service.GenericService;
import com.ace.core.utils.ReflectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;

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
        setViewPrefix(defaultViewPrefix());
    }

    /**
     * 获取视图名称：即prefixViewName + "/" + suffixName
     *
     * @return
     */
    public String viewName(String suffixName) {
        if (!suffixName.startsWith("/")) {
            suffixName = "/" + suffixName;
        }
        return getViewPrefix() + suffixName;
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

    protected String defaultViewPrefix() {
        String currentViewPrefix = "";
        RequestMapping requestMapping = AnnotationUtils.findAnnotation(getClass(), RequestMapping.class);
        if (requestMapping != null && requestMapping.value().length > 0) {
            currentViewPrefix = requestMapping.value()[0];
        }

        if (StringUtils.isEmpty(currentViewPrefix)) {
            currentViewPrefix = this.entityClass.getSimpleName();
        }

        return currentViewPrefix;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if(this.entityClass != null) {
            InjectBaseDependencyHelper.findAndInjectGenericServiceDependency(this);
            Assert.notNull(genericService, "GenericService required, Class is:" + getClass());
        }
    }


    public String getViewPrefix() {
        return viewPrefix;
    }

    public void setViewPrefix(String viewPrefix) {
        this.viewPrefix = viewPrefix;
    }
}
