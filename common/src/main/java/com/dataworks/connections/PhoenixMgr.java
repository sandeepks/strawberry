package com.dataworks.connections;

import com.dataworks.error.ThrowIt;
import com.dataworks.model.ConnectionOptions;

import java.sql.Connection;

/**
 * Created by Sandeep on 5/16/17.
 */
public abstract class PhoenixMgr extends SqlMgr {
    public PhoenixMgr(ConnectionOptions options) {
        super(options);
    }

    @Override
    protected String getDriverClass() {
        return getOptions().getJdbcDriverClass();
    }

    @Override
    protected Connection getConnection() throws ThrowIt {
        return null;
    }

}
