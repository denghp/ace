/**
 * Copyright (c) 2009-2018 https://github.com/denghp
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.ace.commons.mybatis.type;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.google.common.collect.Sets;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * @Author: denghp
 * @Date: 10/23/14 9:59 PM
 * @Description:
 *          将String('1,2,3')的字符串,转换成Set<Long> 类型
 */
public class StringToSetTypeHander extends BaseTypeHandler<Set<Long>> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Set<Long> parameter, JdbcType jdbcType) throws SQLException {
        if (parameter != null) {
            ps.setString(i, parameter.toString());
        } else {
            ps.setTimestamp(i, null);
        }
    }

    @Override
    public Set<Long> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String result = rs.getString(columnName);

        return stringToSet(result,null);
    }

    @Override
    public Set<Long> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String result = rs.getString(columnIndex);
        return stringToSet(result,null);
    }

    @Override
    public Set<Long> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String result = cs.getString(columnIndex);
        return stringToSet(result,null);
    }

    /**
     * 根据string,
     * @param result
     * @param regex 分割正则,默认是','
     * @return
     */
    private Set<Long> stringToSet(String result, String regex) {
        if (StringUtils.isNotBlank(result)) {
            //根据split ',' 转换成list
            List<String> list = Arrays.asList(result.split(StringUtils.isBlank(regex) ? "," : regex));
            if (list == null || list.size() <= 0) {
                return null;
            }
            return Sets.newHashSet(Collections2.transform(list, new Function<String, Long>() {
                @Override
                public Long apply(String itme) {
                    return Long.parseLong(itme);
                }
            }));
        }
        return null;
    }
}
