package com.dataworks.model;

/**
 * Created by Sandeep on 5/22/17.
 */
public enum EntityType {
    ORACLE_TBL("ORACLE_TBL"),
    SYBASE_TBL("SYBASE_TBL"),
    DB2_TBL("DB2_TBL"),
    MSSQL_TBL("MSSQL_TBL"),
    CSV("CSV"),
    PSV("PSV"),
    TSV("TSV"),
    JSON("JSON"),
    PDF("PDF"),
    XML("XML"),
    DAT("DAT"),
    HIVE_WORK_TBL("HIVE_WORK_TBL"),
    HIVE_PSTG_TBL("HIVE_PSTG_TBL"),
    HIVE_ATOMIC_WORK("HIVE_ATOMIC_WORK"),
    HIVE_ATOMIC_TBL("HIVE_ATOMIC_TBL"),
    HIVE_DM_WORK("HIVE_DM_WORK"),
    HIVE_DM_TBL("HIVE_DM_TBL");

    private String val;

    private EntityType(String val) {
        this.val = val;
    }

    public String getVal() {
        return val;
    }
}
