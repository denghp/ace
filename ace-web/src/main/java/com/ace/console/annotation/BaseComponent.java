package com.ace.console.annotation;

import java.lang.annotation.*;

/**
 * Project_Name: ace
 * File: BaseComponent
 * (C) Copyright tuan800 Corporation 2013 All Rights Reserved.
 * User: denghp
 * Date: 11/1/13
 * Time: 6:21 PM
 * 查找注解的字段作为BaseService
 * 即
 * 1、查找对象中的注解了@BaseComponent的对象
 * 2、
 * 调用BaseCRUDController.setAbstractService 设置BaseService
 */
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface BaseComponent {

}