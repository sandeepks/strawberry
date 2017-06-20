package com.dataworks.model;

import com.dataworks.model.Layer;

/**
 * Created by Sandeep on 5/22/17.
 */
public class PartitionStrategy {
    private String appNm;
    private String compNm;
    private String srcEntity;
    private Layer dataSetLayer;
    private String partitionStrategy;

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

    public Layer getDataSetLayer() {
        return dataSetLayer;
    }

    public void setDataSetLayer(Layer dataSetLayer) {
        this.dataSetLayer = dataSetLayer;
    }

    public String getPartitionStrategy() {
        return partitionStrategy;
    }

    public void setPartitionStrategy(String partitionStrategy) {
        this.partitionStrategy = partitionStrategy;
    }
}
