package com.dataworks.throttle;

import com.google.common.util.concurrent.FutureCallback;

import java.util.concurrent.CountDownLatch;

/**
 * Created by Sandeep on 6/1/17.
 */
public class ExtractorCallBack<V> implements FutureCallback<V> {
    private CountDownLatch latch;

    public ExtractorCallBack(CountDownLatch latch) {
        this.latch = latch;
    }

    public void onSuccess(V v) {

        latch.countDown();
    }

    public void onFailure(Throwable throwable) {
        latch.countDown();
    }
}
