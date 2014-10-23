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

    INSERT (".insert"),
    INSERT_BATCH (".insertBatch"),
    UPDATE (".update"),
    UPDATE_CONDITION (".updateCondition"),
    UPDATE_BATCH (".updateBatch"),
    DELETE (".delete"),
    DELETE_CONDITION(".deleteCondition"),
    DELETE_BATCH (".deleteBatch"),
    SELECT(".select"),
    SELECT_ONE(".selectOne"),
    SELECT_LIST(".selectList"),
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
