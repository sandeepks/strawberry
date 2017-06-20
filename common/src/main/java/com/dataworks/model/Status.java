package com.dataworks.model;

/**
 * Created by Sandeep on 5/19/17.
 */
public enum Status {
    RUNNING("Running"),
    COMPLETE("Complete"),
    FAILED("Fail");

    private String val;

    Status(String val) {
        this.val = val;
    }

    public String getVal() {
        return val;
    }
}
