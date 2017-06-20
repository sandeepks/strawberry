package com.dataworks.writer;


import com.dataworks.model.ColumnInfo;
import com.dataworks.extract.CtlsHandler.CtlEnum;
import com.dataworks.hadoop.DatumWriter;
import org.apache.commons.csv.CSVFormat;

import java.util.List;
import java.util.Map;

/**
 * Created by Sandeep on 5/12/17.
 */
public class DelimitedWriter {
    private CSVFormat csvFormat = null;
    private DatumWriter writer = null;

    public DelimitedWriter(String target, Class<? extends Enum<?>> headerEnum, char delimiter, String separator) {
        csvFormat = CSVFormat.newFormat(delimiter).withRecordSeparator(separator).withHeader(headerEnum);
        writer = DatumWriter.getWriter(target);
    }

    public DelimitedWriter(String target, char delimiter, String separator) {
        csvFormat = CSVFormat.newFormat(delimiter).withRecordSeparator(separator);
        writer = DatumWriter.getWriter(target);
    }

    public boolean write(List<ColumnInfo> colList){

        return true;
    }

    public boolean write(Map<CtlEnum, String> data){

        return true;
    }
}
