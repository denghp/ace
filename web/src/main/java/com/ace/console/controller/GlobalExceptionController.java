/**
 * Copyright (c) 2009-2018 https://github.com/denghp
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.ace.console.controller;

import com.ace.console.exception.AceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;

/**
 * @Author: denghp
 * @Date: 10/27/14 11:04 PM
 * @Description:
 */
@ControllerAdvice
public class GlobalExceptionController {

    private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionController.class);

    @ExceptionHandler(AceException.class)
    public String handleCustomException(AceException ex, HttpServletResponse response) {
        LOG.error("AceException : {}", ex);
        response.setStatus(500);
        return "/error-500.html";
    }

    @ExceptionHandler(Exception.class)
    public String handleAllException(Exception ex, HttpServletResponse response) {
        LOG.error("Exception : {}", ex);
        response.setStatus(500);
        return "/error-500.html";
    }
}
