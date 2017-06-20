package com.dataworks.connections;

import com.dataworks.error.ThrowIt;
import com.dataworks.model.ConnectionOptions;
import com.dataworks.model.QueryKey;
import com.dataworks.util.CastUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;
import java.util.Set;

/**
 * Created by Sandeep on 5/17/17.
 */
public class CatalogMgr extends PhoenixMgr {
    private Connection connection;
    private PreparedStatement lastStatement;

    public CatalogMgr(ConnectionOptions options) {
        super(options);
    }

    @Override
    protected String getDriverClass() {
        return options.getJdbcDriverClass();
    }

    @Override
    protected Connection getConnection() throws ThrowIt {
        if (null == this.connection) {
            this.connection = makeConnection();
        }

        return this.connection;
    }

    protected boolean hasOpenConnection() {
        return this.connection != null;
    }

    @Override
    public void close() throws ThrowIt {
        super.release();
        discardConnection(true);
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


    public <T> T query(String sql, Map<QueryKey, Object> paramMap, ResultSetCallback<T> handler) throws ThrowIt {

        try {
            release();
            lastStatement = getStatement(sql, paramMap);
            return handler.onQuery(lastStatement.executeQuery());
        } catch (SQLException e) {
            throw new ThrowIt(e);
        }

    }

    public void upsert(String query, Map<QueryKey, Object> paramMap) throws ThrowIt {
        try {
            release();
            lastStatement = getStatement(query, paramMap);
            lastStatement.execute();
        } catch (SQLException e) {
            throw new ThrowIt(e);
        }
    }


    private PreparedStatement getStatement(String sql, Map<QueryKey, Object> paramMap) throws SQLException, ThrowIt {
        PreparedStatement statement = getConnection().prepareStatement(sql);
        Set<Map.Entry<QueryKey, Object>> entrySet = paramMap.entrySet();
        for (Map.Entry<QueryKey, Object> e : entrySet) {
            final QueryKey key = e.getKey();
            final Object value = e.getValue();
            if (key.getType().equalsIgnoreCase("STRING")) {
                statement.setString(key.getOrdinal(), String.valueOf(value));
            } else if (key.getType().equalsIgnoreCase("int")) {
                statement.setInt(key.getOrdinal(), CastUtils.toInt(value));
            }

        }
        return statement;
    }
}
