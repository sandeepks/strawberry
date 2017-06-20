package com.dataworks.error;

/**
 * Created by Sandeep on 5/17/17.
 */
public enum ErrorCode {
    INGESTION_ERROR(1, "INGESTION ERROR");

    private int errorCode;
    private String errorString;

    ErrorCode(int errorCode, String errorString) {
        this.errorCode = errorCode;
        this.errorString = errorString;
    }
}
