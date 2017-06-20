package com.dataworks.model;

import org.joda.time.DateTime;

/**
 * Created by Sandeep on 5/19/17.
 */
public class AppDataCatalog extends CxnConfig {
    private String srcEntity;
    private long batchId;
    private Layer dataSetLayer;
    private String seq;
    private String srcEntityType;
    private String tgtEntityType;
    private String tgtEntityUrn;
    private char isPartition;
    private String partitionLayout;
    private String partitionUrn;
    private String dsOwner;
    private DateTime dsCreateTm;
    private DateTime dsModifiedTm;
    private long dsSize;
    private String yarnAppIp;
    private char isPii;

    public AppDataCatalog(String lobNm, String appNm, String compNm) {
        super(lobNm, appNm, compNm);
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

    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }

    public String getSrcEntityType() {
        return srcEntityType;
    }

    public void setSrcEntityType(String srcEntityType) {
        this.srcEntityType = srcEntityType;
    }

    public String getTgtEntityType() {
        return tgtEntityType;
    }

    public void setTgtEntityType(String tgtEntityType) {
        this.tgtEntityType = tgtEntityType;
    }

    public String getTgtEntityUrn() {
        return tgtEntityUrn;
    }

    public void setTgtEntityUrn(String tgtEntityUrn) {
        this.tgtEntityUrn = tgtEntityUrn;
    }

    public char getIsPartition() {
        return isPartition;
    }

    public void setIsPartition(char isPartition) {
        this.isPartition = isPartition;
    }

    public String getPartitionLayout() {
        return partitionLayout;
    }

    public void setPartitionLayout(String partitionLayout) {
        this.partitionLayout = partitionLayout;
    }

    public String getPartitionUrn() {
        return partitionUrn;
    }

    public void setPartitionUrn(String partitionUrn) {
        this.partitionUrn = partitionUrn;
    }

    public String getDsOwner() {
        return dsOwner;
    }

    public void setDsOwner(String dsOwner) {
        this.dsOwner = dsOwner;
    }

    public DateTime getDsCreateTm() {
        return dsCreateTm;
    }

    public void setDsCreateTm(String dsCreateTm) {
        this.dsCreateTm = null;
    }

    public DateTime getDsModifiedTm() {
        return dsModifiedTm;
    }

    public void setDsModifiedTm(String dsModifiedTmedTm) {
        this.dsModifiedTm = null;
    }

    public long getDsSize() {
        return dsSize;
    }

    public void setDsSize(long dsSize) {
        this.dsSize = dsSize;
    }

    public String getYarnAppIp() {
        return yarnAppIp;
    }

    public void setYarnAppIp(String yarnAppIp) {
        this.yarnAppIp = yarnAppIp;
    }

    public char isPii() {
        return isPii;
    }

    public void setIsPii(char isPii) {
        this.isPii = isPii;
    }
}
