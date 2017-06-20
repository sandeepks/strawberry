package com.dataworks.model;



/**
 * Created by Sandeep on 5/19/17.
 */
public class TblConfig {
    private String lobNm;
    private String appNm;
    private String compNm;
    private String srcEntity;
    private String connectString;
    private String protocol;
    private String userName;
    private String password;
    private String refreshType;
    private String refreshFreq;
    private String dayOfWeek;
    private String connectOption;
    private String schemaOverruleUrn;
    private ImportOptions importOption;

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

    public String getSrcEntity() {
        return srcEntity;
    }

    public void setSrcEntity(String srcEntity) {
        this.srcEntity = srcEntity;
    }

    public String getConnectString() {
        return connectString;
    }

    public void setConnectString(String connectString) {
        this.connectString = connectString;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRefreshType() {
        return refreshType;
    }

    public void setRefreshType(String refreshType) {
        this.refreshType = refreshType;
    }

    public String getRefreshFreq() {
        return refreshFreq;
    }

    public void setRefreshFreq(String refreshFreq) {
        this.refreshFreq = refreshFreq;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getConnectOption() {
        return connectOption;
    }

    public void setConnectOption(String connectOption) {
        this.connectOption = connectOption;
    }

    public String getSchemaOverruleUrn() {
        return schemaOverruleUrn;
    }

    public void setSchemaOverruleUrn(String schemaOverruleUrn) {
        this.schemaOverruleUrn = schemaOverruleUrn;
    }
}
