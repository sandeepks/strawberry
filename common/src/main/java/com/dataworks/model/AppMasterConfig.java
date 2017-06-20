package com.dataworks.model;

import org.joda.time.DateTime;

import java.util.List;

/**
 * Created by Sandeep on 6/6/17.
 */
public class AppMasterConfig {
    private String lobNm;
    private String appNm;
    private String compNm;
    private DateTime startTime;
    private DateTime endTime;
    private List<String> connectEmails;
    private String hdfsUrn;
    private String hiveUrn;

    public AppMasterConfig() {
        super();
    }

    public String getLobNm() {
        return lobNm;
    }

    public void setLobNm(String lobNm) {
        this.lobNm = lobNm;
    }

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

    public DateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(DateTime startTime) {
        this.startTime = startTime;
    }

    public DateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(DateTime endTime) {
        this.endTime = endTime;
    }

    public List<String> getConnectEmails() {
        return connectEmails;
    }

    public void setConnectEmails(List<String> connectEmails) {
        this.connectEmails = connectEmails;
    }

    public String getHdfsUrn() {
        return hdfsUrn;
    }

    public void setHdfsUrn(String hdfsUrn) {
        this.hdfsUrn = hdfsUrn;
    }

    public String getHiveUrn() {
        return hiveUrn;
    }

    public void setHiveUrn(String hiveUrn) {
        this.hiveUrn = hiveUrn;
    }
}
