package com.ace.core.paginator.support;

import com.ace.core.paginator.dialect.Dialect;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Author: denghp
 * @Date: 14-11-23
 * @Time: 下午9:08
 * @Description:
 */
public class SQLHelp {

    private static Logger logger = LoggerFactory.getLogger(SQLHelp.class);

    /**
     * 查询总纪录数
     *
     * @param mappedStatement mapped
     * @param parameterObject 参数
     * @param boundSql        boundSql
     * @param dialect         database dialect
     * @return 总记录数
     * @throws java.sql.SQLException sql查询错误
     */
    public static int getCount(
            final MappedStatement mappedStatement, final Object parameterObject,
            final BoundSql boundSql, Dialect dialect) throws SQLException {
        final String count_sql = dialect.getCountSQL();
        logger.debug("Total count SQL [{}] ", count_sql);
        logger.debug("Total count Parameters: {} ", parameterObject);

        Connection connection = null;
        PreparedStatement countStmt = null;
        ResultSet rs = null;
        try {
            connection = mappedStatement.getConfiguration().getEnvironment().getDataSource().getConnection();
            countStmt = connection.prepareStatement(count_sql);
            DefaultParameterHandler handler = new DefaultParameterHandler(mappedStatement,parameterObject,boundSql);
            handler.setParameters(countStmt);

            rs = countStmt.executeQuery();
            int count = 0;
            if (rs.next()) {
                count = rs.getInt(1);
            }
            logger.debug("Total count: {}", count);
            return count;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } finally {
                try {
                    if (countStmt != null) {
                        countStmt.close();
                    }
                } finally {
                    if (connection != null && !connection.isClosed()) {
                        connection.close();
                    }
                }
            }
        }
    }

}
