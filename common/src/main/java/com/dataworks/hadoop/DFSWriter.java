package com.dataworks.hadoop;

import java.io.OutputStream;

import org.apache.hadoop.fs.Path;

/**
 * Created by Sandeep on 5/17/17.
 */
public class DFSWriter extends DatumWriter {
	private OutputStream writer;
	
    public DFSWriter() {
        super();
    }

    public void lineWriter(String line) {

    }

    public void bufferWriter(byte[] buffer, int len) {

    }

    public void copyWriter(Path source) {

    }
}
