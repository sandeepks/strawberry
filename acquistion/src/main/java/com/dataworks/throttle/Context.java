package com.dataworks.throttle;

import com.dataworks.model.CxnConfig;
import com.dataworks.model.TblConfig;

import java.util.List;

/**
 * Created by Sandeep on 6/6/17.
 */
public class Context extends CxnConfig {
    private List<CxnConfig> appConnConfig;
    private List<TblConfig> tblConfig;
    private int batchId;

    public Context(String lobNm, String appNm, String compNm, int batchId) {
        super(lobNm, appNm, compNm);
        this.batchId = batchId;
    }

    public List<CxnConfig> getAppConnConfig() {
        return appConnConfig;
    }

    public void setAppConnConfig(List<CxnConfig> appConnConfig) {
        this.appConnConfig = appConnConfig;
    }

    public List<TblConfig> getTblConfig() {
        return tblConfig;
    }

    public void setTblConfig(List<TblConfig> tblConfig) {
        this.tblConfig = tblConfig;
    }

    public int getConnections(){
        return appConnConfig.get(0).getNumOfCxns();
    }

    public short getNumberOfThreads(){
        return (short) getAppConnConfig().size();
    }

}
