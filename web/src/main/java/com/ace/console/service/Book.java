/**
 * Copyright (c) 2009-2018 https://github.com/denghp
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.ace.console.service;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.joda.deser.DateTimeDeserializer;
import com.fasterxml.jackson.datatype.joda.ser.DateTimeSerializer;
import org.joda.time.DateTime;

import java.io.Serializable;

/**
 * @Author: denghp
 * @Date: 10/30/14 8:52 PM
 * @Description:
 */
public class Book implements Serializable {

    private Integer id;
    private String name;
    @JsonSerialize(using = DateTimeSerializer.class)
    private DateTime date;

    public Book(){}

    public Book(Integer id, String name, DateTime createTime) {
        this.id = id;
        this.name = name;
        this.date = createTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DateTime getDate() {
        return date;
    }

    public void setDate(DateTime date) {
        this.date = date;
    }
}
