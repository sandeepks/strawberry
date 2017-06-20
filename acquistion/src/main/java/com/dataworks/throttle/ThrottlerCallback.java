package com.dataworks.throttle;

import com.dataworks.connections.CatalogMgr;
import com.google.common.util.concurrent.FutureCallback;

import java.util.concurrent.CountDownLatch;

/**
 * Created by Sandeep on 5/30/17.
 */
public class ThrottlerCallback<ImportStatus> implements FutureCallback<ImportStatus>{
    private CatalogMgr catalogManager;
    private YarnAppInfoExtractor infoExtractor;
    private CountDownLatch latch;

    public ThrottlerCallback(CountDownLatch latch) {
        this.latch = latch;
    }

    public void onSuccess(ImportStatus importStatus) {
        latch.countDown();

    }

    public void onFailure(Throwable throwable) {
        latch.countDown();

    }
}
