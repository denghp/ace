/**
 * Copyright (c) 2009-2018 https://github.com/denghp
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.ace.commons.json;

import org.codehaus.jackson.map.ObjectMapper;

/**
 * @Author: denghp
 * @Date: 10/19/14 2:00 PM
 * @Description:
 */
public class JsonUtils {

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static ObjectMapper getObjectMapper() {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
        }
        return objectMapper;
    }

}
