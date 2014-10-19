/**
 * Copyright (c) 2009-2018 https://github.com/denghp
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.ace.console.validator;

import com.ace.console.exception.AceException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Iterator;
import java.util.Set;

/**
 * @Author: denghp
 * @Date: 10/19/14 9:20 PM
 * @Description:
 */
public class ValidatorUtils {


    private static Validator validator; // 它是线程安全的

    static {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    /**
     * 验证实体对象的属性
     * @param obj 需要验证的实体类
     * @return
     *        返回错误信息.
     */
    public static String validateObj(Object obj) {
        StringBuffer buffer = new StringBuffer(128);//用于存储验证后的错误信息

        Validator validator = Validation.buildDefaultValidatorFactory()
                .getValidator();

        Set<ConstraintViolation<Object>> constraintViolations = validator
                .validate(obj);//验证某个对象,，其实也可以只验证其中的某一个属性的

        Iterator<ConstraintViolation<Object>> iter = constraintViolations
                .iterator();
        while (iter.hasNext()) {
            String message = iter.next().getMessage();
            buffer.append(message).append(";");
        }
        return buffer.toString();
    }

    public static <T> void validate(T t) throws AceException {
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(t);
        if(constraintViolations.size() > 0) {
            String validateError = "";
            for(ConstraintViolation<T> constraintViolation: constraintViolations) {
                validateError += constraintViolation.getMessage() + ";";
            }
            throw AceException.create(AceException.Code.VALIDATOR_FAILED,validateError);
        }
    }

}
