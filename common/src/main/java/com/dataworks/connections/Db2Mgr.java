package com.dataworks.connections;

import com.dataworks.error.ThrowIt;
import com.dataworks.model.ConnectionOptions;
import com.dataworks.model.QueryKey;

import java.sql.Connection;
import java.util.Map;

/**
 * Created by Sandeep on 5/16/17.
 */
public class Db2Mgr extends SqlMgr {


    public Db2Mgr(ConnectionOptions options) {
        super(options);
    }

    public String getDriverClass() {
        return null;
    }

    public Connection getConnection() throws ThrowIt {
        return null;
    }

    public <T> T query(String Sql, Map<QueryKey, Object> paramMap, ResultSetCallback<T> handler) throws ThrowIt {
        throw new ThrowIt("not supported");
    }

    public void upsert(String query, Map<QueryKey, Object> paramMap) throws ThrowIt {

    }


}
