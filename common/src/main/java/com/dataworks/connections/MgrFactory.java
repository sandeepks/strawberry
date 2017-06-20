package com.dataworks.connections;

import com.dataworks.error.ThrowIt;
import com.dataworks.model.ConnectionOptions;
import com.google.common.collect.ImmutableMap;

import java.lang.reflect.Constructor;

import static com.dataworks.connections.MgrFactory.JDBC_SCHEME.*;


/**
 * Created by Sandeep on 4/20/17.
 */
public class MgrFactory {

    public enum JDBC_SCHEME{
        MYSQL_SCHEME("jdbc:mysql:"),
        POSTGRESQL_SCHEME("jdbc:postgresql:"),
        ORACLE_SCHEME("jdbc:oracle:"),
        DB2_SCHEME("jdbc:db2:"),
        SYBASE_SCHEME("jdbc:sybase:Tds:");

        private String name;

        private JDBC_SCHEME(String name) {
            this.name = name;
        }

		public String getName() {
			return name;
		}
        
    }

    private static final ImmutableMap<JDBC_SCHEME, String> SCHEME_TO_CLASS =
            ImmutableMap.<JDBC_SCHEME, String>builder()
            .put(MYSQL_SCHEME, "com.dataworks.manager.GenericJdbcMgr")
            .put(POSTGRESQL_SCHEME, "com.dataworks.manager.GenericJdbcMgr")
            .put(ORACLE_SCHEME, "com.dataworks.manager.GenericJdbcMgr")
            .put(DB2_SCHEME, "com.dataworks.manager.GenericJdbcMgr")
            .put(SYBASE_SCHEME, "com.dataworks.manager.GenericJdbcMgr")
            .build();

    public static CnxnMgr accept(ConnectionOptions options) throws ThrowIt {

        String scheme = extractScheme(options);
        if (null == scheme) {
            // We don't know if this is a mysql://, hsql://, etc.
            // Can't do anything with this.
            // TODO: LOG "Null scheme associated with connect string."
            return null;
        }

        // TODO: LOG "Trying with scheme: " + scheme

        CnxnMgr cnxnMgr = null;
        String managerClassName = null;
        try {
            managerClassName = SCHEME_TO_CLASS.get(scheme);
            Class<CnxnMgr> cls = (Class<CnxnMgr>)
                    Class.forName(managerClassName);

            // We have two constructor options, one is with or without explicit
            // constructor. In most cases --driver argument won't be allowed as the
            // connectors are forcing to use their building class names.
            Constructor<CnxnMgr> constructor =
                    cls.getDeclaredConstructor(ConnectionOptions.class);
            cnxnMgr = constructor.newInstance(options);
        } catch (ClassNotFoundException e) {
            throw new ThrowIt(e);
        } catch (NoSuchMethodException e) {
            throw new ThrowIt(e);
        } catch (Exception e) {
            throw new ThrowIt(e);
        }
        return cnxnMgr;
    }


    private static String extractScheme(ConnectionOptions options) {
        String connectStr = options.getConnectString();

        // java.net.URL follows RFC-2396 literally, which does not allow a ':'
        // character in the scheme component (section 3.1). JDBC connect strings,
        // however, commonly have a multi-scheme addressing system. e.g.,
        // jdbc:mysql://...; so we cannot parse the scheme component via URL
        // objects. Instead, attempt to pull out the scheme as best as we can.

        // First, see if this is of the form [scheme://hostname-and-etc..]
        int schemeStopIdx = connectStr.indexOf("//");
        if (-1 == schemeStopIdx) {
            // If no hostname start marker ("//"), then look for the right-most ':'
            // character.
            schemeStopIdx = connectStr.lastIndexOf(':');
            if (-1 == schemeStopIdx) {
                /* Warn that this is nonstandard. But we should be as permissive
                   as possible here and let the ConnectionManagers themselves throw
                   out the connect string if it doesn't make sense to them */
                //TODO: log statement

                // Use the whole string.
                schemeStopIdx = connectStr.length();
            }
        }

        return connectStr.substring(0, schemeStopIdx);
    }
}
