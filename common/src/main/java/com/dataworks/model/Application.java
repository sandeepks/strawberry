package com.dataworks.model;

/**
 * Created by Sandeep on 6/6/17.
 */
public class Application {
    private String lobNm;
    private String appNm;
    private String compNm;

    public Application(String lobNm, String appNm, String compNm) {
        this.lobNm = lobNm;
        this.appNm = appNm;
        this.compNm = compNm;
    }

    public String getLobNm() {
        return lobNm;
    }

    public String getAppNm() {
        return appNm;
    }

    public String getCompNm() {
        return compNm;
    }

    public void setLobNm(String lobNm) {
        this.lobNm = lobNm;
    }

    public void setAppNm(String appNm) {
        this.appNm = appNm;
    }

    public void setCompNm(String compNm) {
        this.compNm = compNm;
    }
}
