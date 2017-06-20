package com.dataworks.extract;

import static java.lang.System.lineSeparator;

import java.util.List;

import com.dataworks.connections.CnxnMgr;
import com.dataworks.connections.MgrFactory;
import com.dataworks.error.ThrowIt;
import com.dataworks.model.ColumnInfo;
import com.dataworks.model.ConnectionOptions;
import com.dataworks.writer.DelimitedWriter;
import com.dataworks.writer.Header;

/**
 * Created by Sandeep on 5/17/17.
 */
public class MetasHandler {
	private CnxnMgr manager;
	private String tbl;
	private String writeLocation;
	private ConnectionOptions options;

	public MetasHandler(String tbl, String writeLocation, ConnectionOptions options) throws ThrowIt {
		this.tbl = tbl;
		this.options = options;
		this.manager = MgrFactory.accept(this.options);
		this.writeLocation = writeLocation;
	}

	private List<ColumnInfo> extract() throws ThrowIt {
		return manager.getColumnInfo(tbl);
	}

	private void write(List<ColumnInfo> data) {
		DelimitedWriter writer = new DelimitedWriter(writeLocation, Header.class, '|', lineSeparator());
		writer.write(data);
	}

	public void handle() throws ThrowIt {
		write(extract());
	}

	public static void main(String[] args) throws ThrowIt {
		ConnectionOptions options = new ConnectionOptions.Builder()
				.jdbcDriverClass(null)
				.passWord(null)
				.userName(null)
				.properties(null)
				.fetchSize(100)
				.connectString(null)
				.build();
		
		MetasHandler handler = new MetasHandler(null, "hdfs://", options);
		handler.handle();
		
	}

}
