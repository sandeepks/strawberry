package com.dataworks.model;

import com.google.common.base.Objects;

/**
 * Created by Sandeep on 6/5/17.
 */
public class CxnConfig extends Application {
    private String source;
    private int numOfCxns;
    private String vendor;

    public CxnConfig(String lobNm, String appNm, String compNm) {
        super(lobNm, appNm, compNm);
        this.source = source;
        this.numOfCxns = numOfCxns;
        this.vendor = vendor;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public int getNumOfCxns() {
        return numOfCxns;
    }

    public void setNumOfCxns(int numOfCxns) {
        this.numOfCxns = numOfCxns;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        final CxnConfig other = (CxnConfig) obj;

        return Objects.equal(this.getLobNm(), other.getLobNm())
                && Objects.equal(this.getAppNm(), other.getAppNm())
                && Objects.equal(this.getCompNm(), other.getCompNm())
                && Objects.equal(this.getSource(), other.getSource())
                && Objects.equal(this.getNumOfCxns(), other.getNumOfCxns())
                && Objects.equal(this.getVendor(), other.getVendor());

    }

    @Override
    public int hashCode() {
        return Objects.hashCode(
                this.getLobNm(),
                this.getAppNm(),
                this.getCompNm(),
                this.getSource(),
                this.getNumOfCxns(),
                this.getVendor());
    }
}
