package com.dataworks.throttle;

import com.dataworks.model.CxnConfig;

/**
 * Created by Sandeep on 5/23/17.
 */
public class ImportStatus extends CxnConfig {
    private ImportType type;
    private String yarnApplicationId;
    private String entity;
    private int returnCode;

    public ImportStatus(String lobNm, String appNm, String compNm) {
        super(lobNm, appNm, compNm);
    }
}
