/**
 * Copyright (c) 2009-2018 https://github.com/denghp
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.ace.console.extra;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.code.ssm.providers.CacheTranscoder;
import com.google.code.ssm.providers.CachedObject;
import com.google.code.ssm.providers.CachedObjectImpl;
import net.rubyeye.xmemcached.transcoders.SerializingTranscoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @Author: denghp
 * @Date: 10/30/14 12:01 AM
 * @Description:
 */
public class JacksonSerializingTranscoder implements CacheTranscoder {

    private Logger logger = LoggerFactory.getLogger(JacksonSerializingTranscoder.class);

    private final ObjectMapper objectMapper;

    public JacksonSerializingTranscoder(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public Object decode(CachedObject data) {
        try {
            ByteArrayInputStream bais = new ByteArrayInputStream(data.getData());
            return objectMapper.readTree(bais);
        } catch (IOException ex) {
            throw new IllegalArgumentException("invalid json", ex);
        }
    }

    @Override
    public CachedObject encode(Object o) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            return new CachedObjectImpl(8, objectMapper.writeValueAsBytes(baos));
        } catch (IOException e) {
            logger.warn(String.format("Error serializing object %s", o), e);
            throw new RuntimeException(e);
        } finally {
            try {
                baos.close();
            } catch (IOException e) {
                logger.warn("Error while closing stream", e);
            }
        }
    }

}
