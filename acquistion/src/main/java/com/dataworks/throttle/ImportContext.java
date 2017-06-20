package com.dataworks.throttle;

import com.dataworks.model.CxnConfig;
import com.dataworks.model.ImportOptions;

/**
 * Created by Sandeep on 6/7/17.
 */
public class ImportContext extends CxnConfig {
    private ImportOptions importOptions;
    private ImportType type;

    public ImportContext(String lobNm, String appNm, String compNm) {
        super(lobNm, appNm, compNm);
    }

    public ImportOptions getImportOptions() {
        return importOptions;
    }

    public void setImportOptions(ImportOptions importOptions) {
        this.importOptions = importOptions;
    }
}
