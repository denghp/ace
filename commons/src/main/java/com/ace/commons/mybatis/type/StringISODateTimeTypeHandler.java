package com.ace.commons.mybatis.type;

import com.ace.commons.utils.TimeUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.sql.*;
import java.util.Date;


/**
 * Project_Name: ace
 * File: StringISODateTimeTypeHandler
 * User: denghp
 * Date: 10/30/13
 * Time: 10:05 AM
 */
public class StringISODateTimeTypeHandler extends BaseTypeHandler {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Object parameter, JdbcType jdbcType) throws SQLException {
        String time = (String)parameter;
        if(time.length() <= 10) {
            time = time + " " + DateFormatUtils.ISO_TIME_NO_T_FORMAT.format(new Date());
        }
        DateTime dateTime = DateTime.parse(time, DateTimeFormat.forPattern(TimeUtils.DATETIME_NORMAL_FORMAT));
        ps.setTimestamp(i, new Timestamp(dateTime.getMillis()));
    }

    @Override
    public Object getNullableResult(ResultSet resultSet, String columnName) throws SQLException {
        Timestamp timestamp = resultSet.getTimestamp(columnName);
        if (timestamp != null) {
            return DateFormatUtils.format(timestamp,DateFormatUtils.ISO_DATE_FORMAT.getPattern() + " " + DateFormatUtils.ISO_TIME_NO_T_FORMAT.getPattern());
        }
        return null;
    }

    @Override
    public Object getNullableResult(ResultSet resultSet, int columnIndex) throws SQLException {
        Timestamp timestamp = resultSet.getTimestamp(columnIndex);
        if (timestamp != null) {
            return DateFormatUtils.format(timestamp,DateFormatUtils.ISO_DATE_FORMAT.getPattern() + " " + DateFormatUtils.ISO_TIME_NO_T_FORMAT.getPattern());
        }
        return null;
    }

    @Override
    public Object getNullableResult(CallableStatement callableStatement, int columnIndex) throws SQLException {
        Timestamp timestamp = callableStatement.getTimestamp(columnIndex);
        if (timestamp != null) {
            return DateFormatUtils.format(timestamp,DateFormatUtils.ISO_DATE_FORMAT.getPattern() + " " + DateFormatUtils.ISO_TIME_NO_T_FORMAT.getPattern());
        }
        return null;
    }
}
