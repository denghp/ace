package com.ace.console.dao.enums;

/**
 * @Project_Name: ace-parent
 * @File: NameSpace
 * (C) Copyright ACE Corporation 2014 All Rights Reserved.
 * @Author: denghp
 * @Date: 10/15/14
 * @Time: 11:03 PM
 * @Description:
 */
public enum NameSpace {

    SQLID_INSERT ("insert"),
    SQLID_INSERT_BATCH ("insertBatch"),
    SQLID_UPDATE ("update"),
    SQLID_UPDATE_PARAM ("updateParam"),
    SQLID_UPDATE_BATCH ("updateBatch"),
    SQLID_DELETE ("delete"),
    SQLID_DELETE_PARAM ("deleteParam"),
    SQLID_DELETE_BATCH ("deleteBatch"),
    SQLID_SELECT("select"),
    SQLID_SELECT_ID("selectID"),
    SQLID_SELECT_PARAM("selectParam"),
    SQLID_COUNT("count"),
    SQLID_COUNT_PARAM("countParam");

    private String namespace;

    private NameSpace(String namespace) {
        this.namespace = namespace;
    }

    public String getNamespace() {
        return this.namespace;
    }

}
