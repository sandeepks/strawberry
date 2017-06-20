package com.dataworks.extract;

import com.dataworks.writer.DelimitedWriter;
import com.google.common.collect.ImmutableMap;

import java.util.Map;

import static java.lang.System.lineSeparator;

/**
 * Created by Sandeep on 5/17/17.
 */
public class CtlsHandler {
    public static enum CtlEnum {
        SRC_SCHEMA("SRC_SCHEMA"),
        SRC_TABLE("SRC_TABLE"),
        EXTRACTION_TIME("EXTRACTION_TIME"),
        EXTRACTED_BY("EXTRACTED_BY");

        private String val;

        CtlEnum(String val) {
            this.val = val;
        }

        public String getVal(){
            return val;
        }
    }
    private String writeLocation;

    public void handle(String user, String extractionTime, String tbl, String schema){
        Map<CtlEnum, String> ctlEntries = ImmutableMap.<CtlEnum, String>builder()
                .put(CtlEnum.SRC_SCHEMA, schema)
                .put(CtlEnum.SRC_TABLE, tbl)
                .put(CtlEnum.EXTRACTED_BY, user)
                .put(CtlEnum.EXTRACTION_TIME, extractionTime)
                .build();
        write(ctlEntries);
    }

    public void write(Map<CtlEnum, String> data){
        DelimitedWriter writer = new DelimitedWriter(writeLocation,'|', lineSeparator());
        writer.write(data);
    }

}


