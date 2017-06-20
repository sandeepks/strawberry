package com.dataworks.connections;

import com.dataworks.error.ThrowIt;
import com.dataworks.model.ColumnInfo;
import com.dataworks.model.ConnectionOptions;
import com.dataworks.model.QueryKey;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by Sandeep on 4/19/17.
 */
public class GenericJdbcMgr extends SqlMgr {
    private Connection connection;

    public GenericJdbcMgr(final ConnectionOptions opts) {
        super(opts);
    }

    @Override
    public String getDriverClass() {
        return options.getJdbcDriverClass();
    }

    @Override
    public Connection getConnection() throws ThrowIt {
        if (null == this.connection) {
            this.connection = makeConnection();
        }

        return this.connection;
    }

    protected boolean hasOpenConnection() {
        return this.connection != null;
    }

    /**
     * Any reference to the connection managed by this manager is nulled.
     * If doClose is true, then this method will attempt to close the
     * connection first.
     *
     * @param doClose if true, try to close the connection before forgetting it.
     */
    public void discardConnection(boolean doClose) {
        if (doClose && hasOpenConnection()) {
            try {
                this.connection.close();
            } catch (SQLException sqe) {
            }
        }

        this.connection = null;
    }

    @Override
    public List<ColumnInfo> getColumnInfo(String tableName) throws ThrowIt {
        return super.getColumnInfo(tableName);
    }

    @Override
    public void close() throws ThrowIt {
        super.close();
        discardConnection(true);
    }

    public void upsert(String query, Map<QueryKey, Object> paramMap) throws ThrowIt {
        throw new ThrowIt("Not an allowed operation!");
    }

    public <T> T query(String Sql, Map<QueryKey, Object> paramMap, ResultSetCallback<T> handler) throws ThrowIt {
        throw new ThrowIt("Not an allowed operation!");
    }


}
