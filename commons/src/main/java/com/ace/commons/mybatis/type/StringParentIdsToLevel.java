package com.ace.commons.mybatis.type;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Project_Name: ace
 * File: StringParentIdsToLevel
 * User: denghp
 * Date: 10/30/14
 * Time: 10:05 AM
 */
public class StringParentIdsToLevel extends BaseTypeHandler {

    private static final String separator = "/";

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Object parameter, JdbcType jdbcType) throws SQLException {
        String parentIds = (String)parameter;
        if (parentIds.startsWith(separator)) {
           ps.setInt(i,parentIds.split(separator).length - 2);
        } else {
            ps.setInt(i,parentIds.split(separator).length - 1);
        }
    }

    @Override
    public Object getNullableResult(ResultSet resultSet, String columnName) throws SQLException {
        String parentIds = resultSet.getString(columnName);
        return getLevel(parentIds);
    }

    @Override
    public Object getNullableResult(ResultSet resultSet, int columnIndex) throws SQLException {
        String parentIds = resultSet.getString(columnIndex);
        return getLevel(parentIds);
    }

    @Override
    public Object getNullableResult(CallableStatement callableStatement, int columnIndex) throws SQLException {
        String parentIds = callableStatement.getString(columnIndex);
        return getLevel(parentIds);
    }

    /**
     * 从父级属性中获取当前level
     * @param parentIds
     * @return
     */
    private Integer getLevel(String parentIds) {
        if (StringUtils.isNotBlank(parentIds)) {
            if (parentIds.startsWith(separator)) {
                return parentIds.split(separator).length - 2;
            } else {
                return parentIds.split(separator).length - 1;
            }
        }
        return null;
    }
}
