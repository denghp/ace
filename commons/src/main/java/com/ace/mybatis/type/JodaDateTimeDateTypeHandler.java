package com.ace.mybatis.type;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.joda.time.DateTime;

import java.sql.*;

/**
 * @Project_Name: ace-parent
 * @File: JodaDateTimeDateTypeHandler
 * (C) Copyright ACE Corporation 2013 All Rights Reserved.
 * @Author: denghp
 * @Date: 14-10-17
 * @Time: 下午8:39
 * @Description:
 */
public class JodaDateTimeDateTypeHandler extends BaseTypeHandler<DateTime> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, DateTime parameter, JdbcType jdbcType) throws SQLException {
        DateTime dateTime = (DateTime) parameter;
        ps.setDate(i, new Date(dateTime.getMillis()));
    }

    @Override
    public DateTime getNullableResult(ResultSet resultSet, String columnName) throws SQLException {
        DateTime dateTime = null;
        Date date = resultSet.getDate(columnName);
        if (date != null) {
            dateTime = new DateTime(date.getTime());
        }
        return dateTime;
    }

    @Override
    public DateTime getNullableResult(ResultSet resultSet, int columnIndex) throws SQLException {
        DateTime dateTime = null;
        Date date = resultSet.getDate(columnIndex);
        if (date != null) {
            dateTime = new DateTime(date.getTime());
        }
        return dateTime;
    }

    @Override
    public DateTime getNullableResult(CallableStatement callableStatement, int columnIndex) throws SQLException {
        DateTime dateTime = null;
        Date date = callableStatement.getDate(columnIndex);
        if (date != null) {
            dateTime = new DateTime(date.getTime());
        }
        return dateTime;
    }
}
