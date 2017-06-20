package com.dataworks.model;

import java.util.Properties;

/**
 * Created by Sandeep on 4/19/17.
 */
public class ConnectionOptions {
	private String userName;
	private String password;
	private String connectString;
	private String jdbcDriverClass;
	private Properties properties;
	private int fetchSize;

	private ConnectionOptions(String userName, String password, String connectString, String jdbcDriverClass,
			Properties properties, int fetchSize) {
		super();
		this.userName = userName;
		this.password = password;
		this.connectString = connectString;
		this.jdbcDriverClass = jdbcDriverClass;
		this.properties = properties;
		this.fetchSize = fetchSize;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public String getConnectString() {
		return connectString;
	}

	public String getJdbcDriverClass() {
		return jdbcDriverClass;
	}

	public Properties getConnectionParams() {
		return properties;
	}

	public int getFetchSize() {
		return fetchSize;
	}

	public static class Builder {
		private String userName;
		private String password;
		private String connectString;
		private String jdbcDriverClass;
		private Properties properties;
		private int fetchSize;

		public Builder() {
			super();
		}

		public Builder userName(String uN) {
			this.userName = uN;
			return this;
		}

		public Builder passWord(String location) {
			this.password = location;
			return this;
		}
		
		public Builder connectString(String cn) {
			this.connectString = cn;
			return this;
		}
		
		public Builder jdbcDriverClass(String jdbcDriverClass) {
			this.jdbcDriverClass = jdbcDriverClass;
			return this;
		}
		
		public Builder properties(Properties properties) {
			this.properties = properties;
			return this;
		}
		
		public Builder fetchSize(int fs){
			this.fetchSize = fs;
			return this;
		}
		
		public ConnectionOptions build(){
			return new ConnectionOptions(userName, password, connectString, jdbcDriverClass, properties, fetchSize);
		}

	}
}
