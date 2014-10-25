/**
 * Copyright (c) 2009-2018 https://github.com/denghp
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.ace.console.enums;

/**
 * @Author: denghp
 * @Date: 10/25/14 8:07 PM
 * @Description:
 */
public enum Status {

    ENABLED("有效",1), DISABLE("无效",0);


    private String name;

    private int index;

    private Status(String name, int index) {
        this.name = name;
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
