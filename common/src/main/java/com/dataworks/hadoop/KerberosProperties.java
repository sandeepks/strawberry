package com.dataworks.hadoop;

import java.io.File;

/**
 * Created by Sandeep on 5/12/17.
 */
public class KerberosProperties {
    private File kerberosConfigFile;
    private String kerberosPrincipal;
    private String kerberosKeytab;

    public File getKerberosConfigFile() {
        return kerberosConfigFile;
    }

    public String getKerberosPrincipal() {
        return kerberosPrincipal;
    }

    public String getKerberosKeytab() {
        return kerberosKeytab;
    }
}
