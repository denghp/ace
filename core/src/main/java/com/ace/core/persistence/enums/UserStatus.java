/**
 * Copyright (c) 2009-2018 https://github.com/denghp
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.ace.core.persistence.enums;

/**
 * @Author: denghp
 * @Date: 10/19/14 2:28 PM
 * @Description:
 */
public enum  UserStatus {

    normal("正常状态"), blocked("封禁状态");

    private final String info;

    private UserStatus(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }


}
