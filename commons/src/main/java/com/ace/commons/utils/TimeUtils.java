/**
 * Copyright (c) 2009-2015 http://demi-panda.com
 *
 * Licensed 
 */
package com.ace.commons.utils;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Created with ace.
 * User: denghp
 * Date: 10/28/13
 * Time: 10:47 PM
 */
public class TimeUtils {

    public static final String DATETIME_DATE_NORMAL_FORMAT = "yyyy-MM-dd";
    public static final String DATETIME_NORMAL_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static DateTime parse(String time) {
        DateTimeFormatter sFormat = DateTimeFormat.forPattern(DATETIME_DATE_NORMAL_FORMAT);
        DateTimeFormatter lFormat = DateTimeFormat .forPattern(DATETIME_NORMAL_FORMAT);
        //时间解析
        if (time != null && !time.isEmpty()) {
            return time.length() > 10 ? DateTime.parse(time, lFormat) : DateTime.parse(time,sFormat);
        }
        return null;
    }

}
