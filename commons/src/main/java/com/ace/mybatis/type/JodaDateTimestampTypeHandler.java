package com.ace.mybatis.type;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.joda.time.DateTime;

import java.sql.*;

/**
 * @Project_Name: ace-parent
 * @File: JodaDateTimestampTypeHandler
 * (C) Copyright ACE Corporation 2013 All Rights Reserved.
 * @Author: denghp
 * @Date: 14-10-17
 * @Time: 下午8:44
 * @Description:
 */
public class JodaDateTimestampTypeHandler extends BaseTypeHandler<DateTime> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, DateTime parameter, JdbcType jdbcType) throws SQLException {
        DateTime dateTime = (DateTime) parameter;
        ps.setTimestamp(i, new Timestamp(dateTime.getMillis()));
    }

    @Override
    public DateTime getNullableResult(ResultSet resultSet, String columnName) throws SQLException {
        DateTime dateTime = null;
        Timestamp timestamp = resultSet.getTimestamp(columnName);
        if (timestamp != null) {
            dateTime = new DateTime(timestamp.getTime());
        }
        return dateTime;
    }

    @Override
    public DateTime getNullableResult(ResultSet resultSet, int columnIndex) throws SQLException {
        DateTime dateTime = null;
        Timestamp timestamp = resultSet.getTimestamp(columnIndex);
        if (timestamp != null) {
            dateTime = new DateTime(timestamp.getTime());
        }
        return dateTime;
    }

    @Override
    public DateTime getNullableResult(CallableStatement callableStatement, int columnIndex) throws SQLException {
        DateTime dateTime = null;
        Timestamp timestamp = callableStatement.getTimestamp(columnIndex);
        if (timestamp != null) {
            dateTime = new DateTime(timestamp.getTime());
        }
        return dateTime;
    }
}
