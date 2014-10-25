package com.ace.core.persistence.sys.enums;

/**
 * @Project_Name: ace-parent
 * @File: RdbOperation
 * (C) Copyright ACE Corporation 2014 All Rights Reserved.
 * @Author: denghp
 * @Date: 10/15/14
 * @Time: 10:27 PM
 * @Description:
 */
public enum RdbOperation {

    INSERT(".insert"),
    INSERT_SELECTIVE(".insertSelective"),
    INSERT_BATCH (".insertBatch"),
    UPDATE_BY_PRIMARY_KEY_SELECTIVE (".updateByPrimaryKeySelective"),
    UPDATE_BY_PRIMARY_KEY (".updateByPrimaryKey"),
    UPDATE_CONDITION (".updateCondition"),
    UPDATE_BATCH (".updateBatch"),
    DELETE_BY_PRIMARY_KEY(".deleteByPrimaryKey"),
    DELETE_CONDITION(".deleteCondition"),
    DELETE_BATCH (".deleteBatch"),
    SELECT_BY_PRIMARY_KEY(".selectByPrimaryKey"),
    SELECT_LIST(".selectList"),
    SELECT_ALL(".selectAll"),
    COUNT(".count"),
    COUNT_CONDITION(".countCondition");

    private String value;

    private RdbOperation(String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }

}
