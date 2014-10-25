package com.ace.core.persistence.sys.enums;

/**
 * @Project_Name: ace-parent
 * @File: RdbParams
 * (C) Copyright ACE Corporation 2014 All Rights Reserved.
 * @Author: denghp
 * @Date: 10/15/14
 * @Time: 10:27 PM
 * @Description:
 */
public enum RdbParams {

    ID("id"),
    USER_ID("userId"),
    GROUP_IDS("groupIds");

    private String value;

    private RdbParams(String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }

}
