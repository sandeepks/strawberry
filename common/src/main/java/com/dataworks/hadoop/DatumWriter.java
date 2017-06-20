package com.dataworks.hadoop;

import org.apache.avro.io.DatumReader;
import org.apache.hadoop.fs.Path;

import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Paths;

/**
 * Created by Sandeep on 5/17/17.
 */
public abstract class DatumWriter implements Closeable {
	private OutputStream writer;

	public abstract void lineWriter(String line);

	public abstract void bufferWriter(byte[] buffer, int len);

	public abstract void copyWriter(Path source);

	public void close() throws IOException {
		if (null != writer) {
			writer.close();
		}

	}

	public static DatumWriter getWriter(String path) {
		DatumWriter returnValue = null;
		if (path.startsWith("hdfs:")) {
			returnValue = new DFSWriter();
		}else{
			returnValue = new LocalFSWriter();
		}

		return returnValue;
	}

}
