package com.ace.console.dao.enums;

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

    GET_LIST_PAGES("getListPages");
    private String value;

    private RdbOperation(String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }

}
