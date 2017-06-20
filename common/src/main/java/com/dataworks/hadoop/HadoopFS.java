package com.dataworks.hadoop;

import com.dataworks.error.ThrowIt;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.security.UserGroupInformation;

import java.io.IOException;
import java.security.PrivilegedExceptionAction;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by Sandeep on 5/12/17.
 */
public class HadoopFS {
    private final AtomicReference<HadoopResource> hdfsResources = new AtomicReference<HadoopResource>();
    protected KerberosProperties kerberosProperties;
    private long kerberosReloginThreshold = 9000L;
    private long lastKerberosReloginTime;

    private static Configuration getConfigurationFromResources(String configResources) throws IOException {
        boolean foundResources = false;
        final Configuration config = new Configuration();
        if (null != configResources) {
            String[] resources = configResources.split(",");
            for (String resource : resources) {
                config.addResource(new Path(resource.trim()));
                foundResources = true;
            }
        }

        if (!foundResources) {
            // check that at least 1 non-default resource is available on the classpath
            String configStr = config.toString();
            for (String resource : configStr.substring(configStr.indexOf(":") + 1).split(",")) {
                if (!resource.contains("default") && config.getResource(resource.trim()) != null) {
                    foundResources = true;
                    break;
                }
            }
        }

        if (!foundResources) {
            throw new IOException("Could not find any of the " + "Hadoop Configuration Resources" + " on the classpath");
        }
        return config;
    }

    protected boolean isTicketOld() {
        return (System.currentTimeMillis() / 1000 - lastKerberosReloginTime) > kerberosReloginThreshold;
    }

    protected FileSystem getFileSystem(final Configuration config) throws IOException {
        return FileSystem.get(config);
    }

    protected FileSystem getFileSystemAsUser(final Configuration config, UserGroupInformation ugi) throws IOException {
        try {
            return ugi.doAs(new PrivilegedExceptionAction<FileSystem>() {

                public FileSystem run() throws Exception {
                    return FileSystem.get(config);
                }
            });
        } catch (InterruptedException e) {
            throw new IOException("Unable to create file system: " + e.getMessage());
        }
    }

    protected UserGroupInformation getUserGroupInformation() throws ThrowIt {
        // if kerberos is enabled, check if the ticket should be renewed before returning
        UserGroupInformation userGroupInformation = hdfsResources.get().getUserGroupInformation();
        if (userGroupInformation != null && isTicketOld()) {
            tryKerberosRelogin(userGroupInformation);
        }
        return userGroupInformation;
    }

    protected void tryKerberosRelogin(UserGroupInformation ugi) throws ThrowIt {
        try {
            ugi.checkTGTAndReloginFromKeytab();
            lastKerberosReloginTime = System.currentTimeMillis() / 1000;
        } catch (IOException e) {
            // Most likely case of this happening is ticket is expired and error getting a new one,
            // meaning dfs operations would fail
            throw new ThrowIt("Unable to renew kerberos ticket", e);
        }
    }

    HadoopResource resetHDFSResources(String configResources, String dir, KerberosProperties kerberosProperties) throws IOException {
        Configuration config = getConfigurationFromResources(configResources);

        // disable caching of Configuration and FileSystem objects, else we cannot reconfigure the processor without a complete
        // restart
        String disableCacheName = String.format("fs.%s.impl.disable.cache", FileSystem.getDefaultUri(config).getScheme());
        config.set(disableCacheName, "true");

        // If kerberos is enabled, create the file system as the kerberos principal

        FileSystem fs;
        UserGroupInformation ugi;
        if (SecurityUtil.isSecurityEnabled(config)) {
            String principal = kerberosProperties.getKerberosPrincipal();
            String keyTab = kerberosProperties.getKerberosKeytab();
            ugi = SecurityUtil.loginKerberos(config, principal, keyTab);
            fs = getFileSystemAsUser(config, ugi);
            lastKerberosReloginTime = System.currentTimeMillis() / 1000;
        } else {
            config.set("ipc.client.fallback-to-simple-auth-allowed", "true");
            config.set("hadoop.security.authentication", "simple");
            ugi = SecurityUtil.loginSimple(config);
            fs = getFileSystemAsUser(config, ugi);
        }

        return new HadoopResource(config, fs, ugi);
    }

    static protected class HadoopResource {
        private final Configuration configuration;
        private final FileSystem fileSystem;
        private final UserGroupInformation userGroupInformation;

        public HadoopResource(Configuration configuration, FileSystem fileSystem, UserGroupInformation userGroupInformation) {
            this.configuration = configuration;
            this.fileSystem = fileSystem;
            this.userGroupInformation = userGroupInformation;
        }

        public Configuration getConfiguration() {
            return configuration;
        }

        public FileSystem getFileSystem() {
            return fileSystem;
        }

        public UserGroupInformation getUserGroupInformation() {
            return userGroupInformation;
        }
    }

}
