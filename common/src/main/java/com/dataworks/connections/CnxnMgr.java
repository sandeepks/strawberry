package com.dataworks.connections;


import com.dataworks.error.ThrowIt;
import com.dataworks.model.ColumnInfo;
import com.dataworks.model.QueryKey;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

/**
 * Created by Sandeep on 4/19/17.
 */
public abstract class CnxnMgr {
    protected abstract String getDriverClass();

    protected abstract Connection getConnection() throws ThrowIt;

    public abstract List<ColumnInfo> getColumnInfo(String tableName) throws ThrowIt;

    public abstract void close() throws ThrowIt;

    public abstract<T> T query(String Sql, Map<QueryKey, Object> paramMap, ResultSetCallback<T> handler) throws ThrowIt;

    public abstract void upsert(String query, Map<QueryKey, Object> paramMap) throws ThrowIt;
}
