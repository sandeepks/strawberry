package com.dataworks.connections;


import com.dataworks.error.ThrowIt;
import com.dataworks.model.ColumnInfo;
import com.dataworks.model.ConnectionOptions;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;

import java.sql.*;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Sandeep on 4/19/17.
 */
public abstract class SqlMgr extends CnxnMgr {

    protected ConnectionOptions options;
    private Statement lastStatement;

    public SqlMgr(ConnectionOptions options) {
        this.options = options;

    }

    protected Connection makeConnection() throws ThrowIt {

        Connection returnValue = null;
        String driverClass = getDriverClass();

        try {
            Class.forName(driverClass);
            String username = options.getUserName();
            String password = options.getPassword();
            String connectString = options.getConnectString();
            Properties connectionParams = options.getConnectionParams();
            if (connectionParams != null && connectionParams.size() > 0) {

                Properties props = new Properties();
                if (username != null) {
                    props.put("user", username);
                }

                if (password != null) {
                    props.put("password", password);
                }

                props.putAll(connectionParams);
                returnValue = DriverManager.getConnection(connectString, props);
            } else {

                if (username == null) {
                    returnValue = DriverManager.getConnection(connectString);
                } else {
                    returnValue = DriverManager.getConnection(
                            connectString, username, password);
                }
            }

            // We only use this for metadata queries. Loosest semantics are okay.
            returnValue.setTransactionIsolation(getMetadataIsolationLevel());
            returnValue.setAutoCommit(false);
        } catch (ClassNotFoundException  cnfe) {
            throw new RuntimeException("Could not load db driver class: "
                    + driverClass);
        } catch ( SQLException e) {
            throw new ThrowIt(e);
        }

        return returnValue;
    }

    protected int getMetadataIsolationLevel() {
        return Connection.TRANSACTION_READ_COMMITTED;
    }

    @Override
    public void close() throws ThrowIt {
        release();
    }

    public void release() throws ThrowIt {
        if (null != this.lastStatement) {
            try {
                this.lastStatement.close();
            } catch (SQLException e) {
                throw new ThrowIt(e);
            }

            this.lastStatement = null;
        }
    }

    @Override
    public List<ColumnInfo> getColumnInfo(String tableName) throws ThrowIt {
        List<ColumnInfo> returnValue = Lists.<ColumnInfo>newLinkedList();
        ResultSet columns = null;
        try {
            DatabaseMetaData metaData = this.getConnection().getMetaData();
            columns = metaData.getColumns(null, null, tableName, null);

            ResultSet primaryKeys = metaData.getPrimaryKeys(null, null, tableName);
            ImmutableMap.Builder<String, Short> pkMapBuilder = ImmutableMap.<String, Short>builder();

            while (primaryKeys.next()) {
                pkMapBuilder.put(primaryKeys.getString("COLUMN_NAME"),
                        primaryKeys.getShort("KEY_SEQ"));
            }
            Map<String, Short> pkMap = pkMapBuilder.build();

            while (columns.next()) {
                ColumnInfo.ColumnInfoBuilder builder = new ColumnInfo.ColumnInfoBuilder();
                String columnName = columns.getString("COLUMN_NAME");
                builder.columnName(columnName);
                builder.sqlDataType(columns.getString("TYPE_NAME"));
                builder.isNull(columns.getString("IS_NULLABLE"));
                builder.isPrimary(isPrimary(columnName, pkMap));
                builder.ordinalIdx(columns.getInt("ORDINAL_POSITION"));
                builder.decimalDigits(columns.getInt("DECIMAL_DIGITS"));
                builder.characterOctetLength(columns.getInt("CHAR_OCTET_LENGTH"));
            }


        } catch (SQLException e) {
            throw new ThrowIt(e);
        } finally {
            try {
                columns.close();
                getConnection().commit();
            } catch (SQLException e) {
                throw new ThrowIt(e);
            }

        }

        return returnValue;
    }

    protected boolean isPrimary(String columnName, Map<String, Short> pkMap) {
        return (null != pkMap.get(columnName));
    }

    protected ResultSet execute(String stmt, Object... args) throws ThrowIt {
        return execute(stmt, options.getFetchSize(), args);
    }

    protected ResultSet execute(String stmt, Integer fetchSize, Object... args)
            throws ThrowIt {

        ResultSet returnValue = null;

        // Release any previously-open statement.
        release();
        try {
            PreparedStatement statement = this.getConnection().prepareStatement(stmt,
                    ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            if (fetchSize != null) {
                statement.setFetchSize(fetchSize);
            }

            this.lastStatement = statement;
            if (null != args) {
                for (int i = 0; i < args.length; i++) {
                    statement.setObject(i + 1, args[i]);
                }
            }

            returnValue = statement.executeQuery();
        } catch (SQLException e) {
            throw new ThrowIt(e);
        }

        return returnValue;
    }

    public ConnectionOptions getOptions() {
        return options;
    }
}
