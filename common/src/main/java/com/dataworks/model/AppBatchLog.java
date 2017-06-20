package com.dataworks.model;

import com.dataworks.model.Layer;
import com.dataworks.model.Status;

/**
 * Created by Sandeep on 5/22/17.
 */
public class AppBatchLog {
    private String appNm;
    private String compNm;
    private String srcEntity;
    private long batchId;
    private Layer dataSetLayer;
    private String opCode;
    private String seq;
    private String processName;
    private String startTime;
    private String endTime;
    private long srcRecCount;
    private long tgtRecCount;
    private Status status;
    private String logLocation;
    private String yarnApplicationId;

    public String getAppNm() {
        return appNm;
    }

    public void setAppNm(String appNm) {
        this.appNm = appNm;
    }

    public String getCompNm() {
        return compNm;
    }

    public void setCompNm(String compNm) {
        this.compNm = compNm;
    }

    public String getSrcEntity() {
        return srcEntity;
    }

    public void setSrcEntity(String srcEntity) {
        this.srcEntity = srcEntity;
    }

    public long getBatchId() {
        return batchId;
    }

    public void setBatchId(long batchId) {
        this.batchId = batchId;
    }

    public Layer getDataSetLayer() {
        return dataSetLayer;
    }

    public void setDataSetLayer(Layer dataSetLayer) {
        this.dataSetLayer = dataSetLayer;
    }

    public String getOpCode() {
        return opCode;
    }

    public void setOpCode(String opCode) {
        this.opCode = opCode;
    }

    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public long getSrcRecCount() {
        return srcRecCount;
    }

    public void setSrcRecCount(long srcRecCount) {
        this.srcRecCount = srcRecCount;
    }

    public long getTgtRecCount() {
        return tgtRecCount;
    }

    public void setTgtRecCount(long tgtRecCount) {
        this.tgtRecCount = tgtRecCount;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getLogLocation() {
        return logLocation;
    }

    public void setLogLocation(String logLocation) {
        this.logLocation = logLocation;
    }

    public String getYarnApplicationId() {
        return yarnApplicationId;
    }

    public void setYarnApplicationId(String yarnApplicationId) {
        this.yarnApplicationId = yarnApplicationId;
    }
}
