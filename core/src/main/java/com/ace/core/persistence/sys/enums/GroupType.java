package com.ace.core.persistence.sys.enums;

/**
 * @Project_Name: ace-parent
 * @File: GroupType
 * (C) Copyright ACE Corporation 2013 All Rights Reserved.
 * @Author: denghp
 * @Date: 14-10-24
 * @Time: 下午2:51
 * @Description:
 */
public enum  GroupType {

    user("用户组"), organization("组织机构组");

    private final String info;

    private GroupType(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

}
