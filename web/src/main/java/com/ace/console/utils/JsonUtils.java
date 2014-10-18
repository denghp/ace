package com.ace.console.utils;

import org.codehaus.jackson.map.ObjectMapper;

/**
 * @Project_Name: ace
 * @File: JsonUtils
 * (C) Copyright ACE Corporation 2014 All Rights Reserved.
 * @Author: denghp
 * @Date: 10/18/14
 * @Time: 5:54 PM
 * @Description:
 */
public class JsonUtils {

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static ObjectMapper getObjMapper() {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
        }
        return objectMapper;
    }
}
