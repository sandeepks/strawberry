package com.dataworks.connections;

import com.dataworks.error.ThrowIt;

import java.sql.ResultSet;

/**
 * Created by Sandeep on 5/18/17.
 */
public interface ResultSetCallback<T> {
    T onQuery(ResultSet rs) throws ThrowIt;
}
