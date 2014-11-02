/**
 * Copyright (c) 2005-2012 https://github.com/zhangkaitao
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.ace.console.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>User: denghp
 * <p>Date: 13-10-15 下午5:14
 * <p>Version: 1.0
 */
public interface Constants {
    /**
     * 操作名称
     */
    String OP_NAME = "op";


    /**
     * 消息key
     */
    String MESSAGE = "message";

    /**
     * 错误key
     */
    String ERROR = "error";

    /**
     * 上个页面地址
     */
    String BACK_URL = "BackURL";

    String IGNORE_BACK_URL = "ignoreBackURL";

    /**
     * 当前请求的地址 带参数
     */
    String CURRENT_URL = "currentURL";

    /**
     * 当前请求的地址 不带参数
     */
    String NO_QUERYSTRING_CURRENT_URL = "noQueryStringCurrentURL";

    String CONTEXT_PATH = "ctx";

    /**
     * 当前登录的用户
     */
    String CURRENT_USER = "user";
    String CURRENT_USERNAME = "username";

    String ENCODING = "UTF-8";

    Map<String,String> ERRORS = new HashMap<String,String>();

    /**
     * simple-spring-memcache 默认cache name
     */
    String DEFAULT_MM_CACHE_NAME = "default";

    /**
     * 应用服务名称
     */
    String DEFAULT_PROJECT_NAME = "ace";

    String DEFAULT_NAMESPACE = "df_namespace";
}
