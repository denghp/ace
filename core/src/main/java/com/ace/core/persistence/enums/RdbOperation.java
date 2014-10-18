package com.ace.core.persistence.enums;

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

    INSERT (".Mapper.insert"),
    INSERT_BATCH (".Mapper.insertBatch"),
    UPDATE (".Mapper.update"),
    UPDATE_CONDITION (".Mapper.updateCondition"),
    UPDATE_BATCH (".Mapper.updateBatch"),
    DELETE (".Mapper.delete"),
    DELETE_CONDITION(".Mapper.deleteCondition"),
    DELETE_BATCH (".Mapper.deleteBatch"),
    SELECT(".Mapper.select"),
    SELECT_ID(".Mapper.selectID"),
    SELECT_CONDITION(".Mapper.selectCondition"),
    SELECT_LIST(".Mapper.selectList"),
    COUNT(".Mapper.count"),
    COUNT_CONDITION(".Mapper.countCondition");

    private String value;

    private RdbOperation(String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }

}
